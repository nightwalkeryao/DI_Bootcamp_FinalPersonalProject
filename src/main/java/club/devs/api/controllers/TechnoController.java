package club.devs.api.controllers;

import club.devs.api.models.Techno;
import club.devs.api.repositories.TechnoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/technos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TechnoController {
    private final TechnoRepository repository;

    public TechnoController(TechnoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Techno> index(){
        return (List<Techno>) repository.findAll();
    }

    @PostMapping
    public Techno store(@Validated @RequestBody Techno t) {
        return repository.save(t);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Techno> show(@PathVariable(value = "id") Integer id) {
        Optional<Techno> s = repository.findById(id);
        return s.isPresent()
                ? ResponseEntity.ok().body(s.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Techno update(@PathVariable(value = "id") Integer id, @RequestBody Techno techno) {
        return repository.findById(id) //
                .map(t -> {
                    t.setName(techno.getName());
                    t.setLogo(techno.getLogo());
                    return repository.save(t);
                }) //
                .orElseGet(() -> {
                    techno.setId(id);
                    return repository.save(techno);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}
