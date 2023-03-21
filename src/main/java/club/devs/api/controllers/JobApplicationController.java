package club.devs.api.controllers;

import club.devs.api.models.JobApplication;
import club.devs.api.repositories.JobApplicationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-applications")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JobApplicationController {
    private final JobApplicationRepository repository;

    public JobApplicationController(JobApplicationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<JobApplication> index(){
        return (List<JobApplication>) repository.findAll();
    }

    @PostMapping
    public JobApplication store(@Validated @RequestBody JobApplication ja) {
        return repository.save(ja);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> show(@PathVariable(value = "id") Integer id) {
        Optional<JobApplication> role = repository.findById(id);
        return role.isPresent()
                ? ResponseEntity.ok().body(role.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    JobApplication update(@PathVariable(value = "id") Integer id, @RequestBody JobApplication ja) {
        return repository.findById(id) //
                .map(j -> {
                    j.setApplicantType(ja.getApplicantType());
                    j.setApplicantId(ja.getApplicantId());
                    j.setJobPost(ja.getJobPost());
                    return repository.save(j);
                }) //
                .orElseGet(() -> {
                    ja.setId(id);
                    return repository.save(ja);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}
