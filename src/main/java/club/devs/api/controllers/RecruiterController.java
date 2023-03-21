package club.devs.api.controllers;

import club.devs.api.dtos.RecruiterDTO;
import club.devs.api.models.Recruiter;
import club.devs.api.models.User;
import club.devs.api.repositories.RecruiterRepository;
import club.devs.api.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recruiters")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecruiterController {
    private final RecruiterRepository repository;
    private final UserRepository userRepository;

    public RecruiterController(RecruiterRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Recruiter> index() {
        return (List<Recruiter>) repository.findAll();
    }

    @PostMapping
    public Recruiter store(@Validated @RequestBody RecruiterDTO recruiterDTO) {
        Recruiter recruiter = new Recruiter();
        ModelMapper mm = new ModelMapper();
        mm.map(recruiterDTO, recruiter);
        Optional<User> user = userRepository.findById(recruiterDTO.getUser());
        if(user.isPresent())
            recruiter.setUser(user.get());

        return repository.save(recruiter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recruiter> show(@PathVariable(value = "id") Integer id) {
        Optional<Recruiter> recruiter = repository.findById(id);
        return recruiter.isPresent()
                ? ResponseEntity.ok().body(recruiter.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Recruiter update(@PathVariable(value = "id") Integer id, @RequestBody Recruiter recruiter) {
        return repository.findById(id) //
                .map(r -> {
                    r.setUser(recruiter.getUser());
                    r.setName(recruiter.getName());
                    r.setCompany(recruiter.getCompany());
                    r.setProfilePicture(recruiter.getProfilePicture());
                    r.setPhoneNumber(recruiter.getPhoneNumber());
                    r.setJobTitle(recruiter.getJobTitle());
                    return repository.save(r);
                }) //
                .orElseGet(() -> {
                    recruiter.setId(id);
                    return repository.save(recruiter);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}