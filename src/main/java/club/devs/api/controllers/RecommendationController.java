package club.devs.api.controllers;

import club.devs.api.models.Recommendation;
import club.devs.api.repositories.RecommendationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecommendationController {
    private final RecommendationRepository repository;

    public RecommendationController(RecommendationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Recommendation> index(){
        return (List<Recommendation>) repository.findAll();
    }

    @PostMapping
    public Recommendation store(@Validated @RequestBody Recommendation recommendation) {
        return repository.save(recommendation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recommendation> show(@PathVariable(value = "id") Integer id) {
        Optional<Recommendation> rec = repository.findById(id);
        return rec.isPresent()
                ? ResponseEntity.ok().body(rec.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Recommendation update(@PathVariable(value = "id") Integer id, @RequestBody Recommendation recommendation) {
        return repository.findById(id) //
                .map(r -> {
                    r.setMentor(recommendation.getMentor());
                    r.setMentored(recommendation.getMentored());
                    r.setJobPost(recommendation.getJobPost());
                    return repository.save(r);
                }) //
                .orElseGet(() -> {
                    recommendation.setId(id);
                    return repository.save(recommendation);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}
