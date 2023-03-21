package club.devs.api.controllers;

import club.devs.api.dtos.MentorDTO;
import club.devs.api.models.Mentored;
import club.devs.api.models.Role;
import club.devs.api.models.Status;
import club.devs.api.models.User;
import club.devs.api.repositories.MentoredRepository;
import club.devs.api.repositories.StatusRepository;
import club.devs.api.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mentored")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MentoredController {
    private final MentoredRepository repository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    public MentoredController(MentoredRepository repository, StatusRepository statusRepository, UserRepository userRepository) {
        this.repository = repository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Mentored> index() {
        return (List<Mentored>) repository.findAll();
    }

    @PostMapping
    public Mentored store(@Validated @RequestBody MentorDTO mentoredDTO) {
        ModelMapper mm = new ModelMapper();
        Mentored mtrd = new Mentored();
        mm.map(mentoredDTO, mtrd);
        Optional<User> user = userRepository.findById(mentoredDTO.getUser());
        if(user.isPresent())
            mtrd.setUser(user.get());
        Optional<Status> status = statusRepository.findById(mentoredDTO.getStatus());
        if(status.isPresent())
            mtrd.setStatus(status.get());

        return repository.save(mtrd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentored> show(@PathVariable(value = "id") Integer id) {
        Optional<Mentored> mentored = repository.findById(id);
        return mentored.isPresent()
                ? ResponseEntity.ok().body(mentored.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Mentored update(@PathVariable(value = "id") Integer id, @RequestBody Mentored mentored) {
        return repository.findById(id) //
                .map(m -> {
                    m.setUser(mentored.getUser());
                    m.setMentor(mentored.getMentor());
                    m.setName(mentored.getName());
                    m.setProfilePicture(m.getProfilePicture());
                    m.setCity(mentored.getCity());
                    m.setBalance(mentored.getBalance());
                    m.setStatus(mentored.getStatus());
                    return repository.save(m);
                }) //
                .orElseGet(() -> {
                    mentored.setId(id);
                    return repository.save(mentored);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}