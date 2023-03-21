package club.devs.api.controllers;

import club.devs.api.models.Review;
import club.devs.api.repositories.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {
    private final ReviewRepository repository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Review> index(){
        return (List<Review>) repository.findAll();
    }

    @PostMapping
    public Review store(@Validated @RequestBody Review review) {
        return repository.save(review);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> show(@PathVariable(value = "id") Integer id) {
        Optional<Review> rv = repository.findById(id);
        return rv.isPresent()
                ? ResponseEntity.ok().body(rv.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Review update(@PathVariable(value = "id") Integer id, @RequestBody Review review) {
        return repository.findById(id) //
                .map(r -> {
                    r.setReviewerType(review.getReviewerType());
                    r.setReviewerId(review.getReviewerId());
                    r.setRating(review.getRating());
                    r.setDescription(review.getDescription());
                    return repository.save(r);
                }) //
                .orElseGet(() -> {
                    review.setId(id);
                    return repository.save(review);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}
