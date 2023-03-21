package club.devs.api.controllers;

import club.devs.api.models.BadgeEarned;
import club.devs.api.repositories.BadgeEarnedRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/badges-earned")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BadgeEarnedController {
    private final BadgeEarnedRepository repository;

    public BadgeEarnedController(BadgeEarnedRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<BadgeEarned> index(){
        return (List<BadgeEarned>) repository.findAll();
    }

    @PostMapping
    public BadgeEarned store(@Validated @RequestBody BadgeEarned b) {
        return repository.save(b);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BadgeEarned> show(@PathVariable(value = "id") Integer id) {
        Optional<BadgeEarned> s = repository.findById(id);
        return s.isPresent()
                ? ResponseEntity.ok().body(s.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    BadgeEarned update(@PathVariable(value = "id") Integer id, @RequestBody BadgeEarned badge) {
        return repository.findById(id) //
                .map(b -> {
                    b.setBadge(badge.getBadge());
                    b.setEarnerType(badge.getEarnerType());
                    b.setEarnerId(badge.getEarnerId());
                    b.setPoints(badge.getPoints());
                    b.setUsedAt(badge.getUsedAt());
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
