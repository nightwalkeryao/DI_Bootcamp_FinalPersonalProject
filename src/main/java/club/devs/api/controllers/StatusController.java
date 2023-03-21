package club.devs.api.controllers;

import club.devs.api.models.Status;
import club.devs.api.repositories.StatusRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/statuses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatusController {
    private final StatusRepository repository;

    public StatusController(StatusRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Status> index() {
        return (List<Status>) repository.findAll();
    }

    @PostMapping
    public Status store(@Validated @RequestBody Status s) {
        return repository.save(s);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> show(@PathVariable(value = "id") Integer id) {
        Optional<Status> s = repository.findById(id);
        return s.isPresent()
                ? ResponseEntity.ok().body(s.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Status update(@PathVariable(value = "id") Integer id, @RequestBody Status status) {
        return repository.findById(id) //
                .map(s -> {
                    s.setName(status.getName());
                    s.setColor(status.getColor());
                    return repository.save(s);
                }) //
                .orElseGet(() -> {
                    status.setId(id);
                    return repository.save(status);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}
