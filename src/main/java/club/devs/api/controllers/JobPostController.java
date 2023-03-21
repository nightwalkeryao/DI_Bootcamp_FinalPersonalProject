package club.devs.api.controllers;

import club.devs.api.models.JobPost;
import club.devs.api.repositories.JobPostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JobPostController {
    private final JobPostRepository repository;

    public JobPostController(JobPostRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<JobPost> index(){
        return (List<JobPost>) repository.findAll();
    }

    @PostMapping
    public JobPost store(@Validated @RequestBody JobPost jp) {
        return repository.save(jp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPost> show(@PathVariable(value = "id") Integer id) {
        Optional<JobPost> role = repository.findById(id);
        return role.isPresent()
                ? ResponseEntity.ok().body(role.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    JobPost update(@PathVariable(value = "id") Integer id, @RequestBody JobPost jp) {
        return repository.findById(id) //
                .map(j -> {
                    j.setTitle(jp.getTitle());
                    j.setContractType(jp.getContractType());
                    j.setDescription(jp.getDescription());
                    j.setCompany(jp.getCompany());
                    j.setRecruiter(jp.getRecruiter());
                    j.setStatus(jp.getStatus());
                    return repository.save(j);
                }) //
                .orElseGet(() -> {
                    jp.setId(id);
                    return repository.save(jp);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}
