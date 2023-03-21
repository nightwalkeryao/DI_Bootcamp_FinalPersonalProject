package club.devs.api.controllers;

import club.devs.api.dtos.MentorDTO;
import club.devs.api.models.Mentor;
import club.devs.api.models.Status;
import club.devs.api.models.User;
import club.devs.api.repositories.MentorRepository;
import club.devs.api.repositories.StatusRepository;
import club.devs.api.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/mentors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MentorController {
    private final MentorRepository repository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    public MentorController(MentorRepository repository, UserRepository userRepository, StatusRepository statusRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public List<Mentor> index() {
        return (List<Mentor>) repository.findAll();
    }

    @PostMapping
    public Mentor store(@Validated @RequestBody MentorDTO mentorDTO) {
        ModelMapper mm = new ModelMapper();
        Mentor mentor = new Mentor();
        mm.map(mentorDTO, mentor);
        Optional<User> user = userRepository.findById(mentorDTO.getUser());
        if(user.isPresent())
            mentor.setUser(user.get());
        Optional<Status> status = statusRepository.findById(mentorDTO.getStatus());
        if(status.isPresent())
            mentor.setStatus(status.get());

        return repository.save(mentor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentor> show(@PathVariable(value = "id") Integer id) {
        Optional<Mentor> mentor = repository.findById(id);
        return mentor.isPresent()
                ? ResponseEntity.ok().body(mentor.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Mentor update(@PathVariable(value = "id") Integer id, @RequestBody Mentor mentor) {
        return repository.findById(id) //
                .map(m -> {
                    m.setUser(mentor.getUser());
                    m.setName(mentor.getName());
                    m.setProfilePicture(m.getProfilePicture());
                    m.setCity(mentor.getCity());
                    m.setBalance(mentor.getBalance());
                    m.setStatus(mentor.getStatus());
                    return repository.save(m);
                }) //
                .orElseGet(() -> {
                    mentor.setId(id);
                    return repository.save(mentor);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}