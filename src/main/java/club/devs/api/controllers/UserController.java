package club.devs.api.controllers;

import club.devs.api.dtos.Login;
import club.devs.api.dtos.UserDTO;
import club.devs.api.models.Role;
import club.devs.api.models.User;
import club.devs.api.repositories.RoleRepository;
import club.devs.api.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    private final UserRepository repository;
    private final RoleRepository roleRepository;

    public UserController(UserRepository repository, RoleRepository rr) {
        this.roleRepository = rr;
        this.repository = repository;
    }

    private @NotNull String generateNewToken() {
        Integer token = new Random().nextInt(1_000_000, 9_999_999);
        return token.toString();
    }

    @GetMapping
    public List<User> index(){
        return (List<User>) repository.findAll();
    }

    @PostMapping
    public User store(@Validated @RequestBody UserDTO userDTO) {
        userDTO.setToken(generateNewToken());
        ModelMapper mm = new ModelMapper();
        User user = new User();
        mm.map(userDTO, user);
        Optional<Role> role = this.roleRepository.findById(userDTO.getRole());
        if(role.isPresent()) {
            user.setRole(role.get());
        }
        return repository.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> show(@PathVariable(value = "id") Integer id) {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()) {
            UserDTO userDTO = mapUserToDTO(user);
            return ResponseEntity.ok().body(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private UserDTO mapUserToDTO(Optional<User> user) {
        UserDTO userDTO = new UserDTO();
        if(user.isPresent()) {
            User u = user.get();
            userDTO.setUsername(u.getUsername());
            userDTO.setToken(u.getToken());
            userDTO.setPassword(u.getPassword());
            userDTO.setEmail(u.getEmail());
            userDTO.setId(u.getId());
//            if(!u.getRole().equals(null)) {
//                userDTO.setRole();
//            }
        }
        return userDTO;
    }

    @PutMapping("/{id}")
    User update(@PathVariable(value = "id") Integer id, @RequestBody User user) {
        return repository.findById(id) //
                .map(u -> {
                    u.setUsername(user.getUsername());
                    u.setEmail(user.getEmail());
                    u.setPassword(user.getPassword());
                    u.setToken(generateNewToken());
                    return repository.save(u);
                }) //
                .orElseGet(() -> {
                    user.setId(id);
                    return repository.save(user);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<User> login(@RequestBody Login login) {
        Optional<User> user = repository.findByCredentials(login.getUsername(), login.getPassword());
        if(user.isPresent()) {
            User u = user.get();
            u.getRole();
            return ResponseEntity.ok().body(u);
        }
        return ResponseEntity.notFound().build();
    }
}
