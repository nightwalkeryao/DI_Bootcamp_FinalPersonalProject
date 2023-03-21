package club.devs.api.controllers;

import club.devs.api.models.Newsletter;
import club.devs.api.repositories.NewsletterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/newsletters")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NewsletterController {
    private final NewsletterRepository repository;

    public NewsletterController(NewsletterRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Newsletter> index(){
        return (List<Newsletter>) repository.findAll();
    }

    @PostMapping
    public Newsletter store(@Validated @RequestBody Newsletter nl) {
        return repository.save(nl);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Newsletter> show(@PathVariable(value = "id") Integer id) {
        Optional<Newsletter> nl = repository.findById(id);
        return nl.isPresent()
                ? ResponseEntity.ok().body(nl.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Newsletter update(@PathVariable(value = "id") Integer id, @RequestBody Newsletter nl) {
        return repository.findById(id) //
                .map(n -> {
                    n.setTitle(nl.getTitle());
                    n.setText(nl.getText());
                    return repository.save(n);
                }) //
                .orElseGet(() -> {
                    nl.setId(id);
                    return repository.save(nl);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}
