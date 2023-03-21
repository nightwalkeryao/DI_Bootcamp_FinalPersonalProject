package club.devs.api.controllers;

import club.devs.api.models.Badge;
import club.devs.api.repositories.BadgeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/badges")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BadgeController {
    private final BadgeRepository repository;

    public BadgeController(BadgeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Badge> index(){
        return (List<Badge>) repository.findAll();
    }

    @PostMapping
    public Badge store(@Validated @RequestBody Badge b) {
        return repository.save(b);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Badge> show(@PathVariable(value = "id") Integer id) {
        Optional<Badge> s = repository.findById(id);
        return s.isPresent()
                ? ResponseEntity.ok().body(s.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Badge update(@PathVariable(value = "id") Integer id, @RequestBody Badge badge) {
        return repository.findById(id) //
                .map(b -> {
                    b.setName(badge.getName());
                    b.setDescription(badge.getDescription());
                    b.setImage(badge.getImage());
                    return repository.save(b);
                }) //
                .orElseGet(() -> {
                    badge.setId(id);
                    return repository.save(badge);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}
