package club.devs.api.controllers;

import club.devs.api.models.Role;
import club.devs.api.repositories.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {
    private final RoleRepository repository;

    public RoleController(RoleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Role> index(){
        return (List<Role>) repository.findAll();
    }

    @PostMapping
    public Role store(@Validated @RequestBody Role role) {
        return repository.save(role);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> show(@PathVariable(value = "id") Integer id) {
        Optional<Role> role = repository.findById(id);
        return role.isPresent()
                ? ResponseEntity.ok().body(role.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Role update(@PathVariable(value = "id") Integer id, @RequestBody Role role) {
        return repository.findById(id) //
                .map(r -> {
                    r.setName(role.getName());
                    return repository.save(r);
                }) //
                .orElseGet(() -> {
                    role.setId(id);
                    return repository.save(role);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}
