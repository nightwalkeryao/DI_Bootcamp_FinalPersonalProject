package club.devs.api.controllers;

import club.devs.api.models.Skill;
import club.devs.api.repositories.SkillRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SkillController {
    private final SkillRepository repository;

    public SkillController(SkillRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Skill> index(){
        return (List<Skill>) repository.findAll();
    }

    @PostMapping
    public Skill store(@Validated @RequestBody Skill s) {
        return repository.save(s);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> show(@PathVariable(value = "id") Integer id) {
        Optional<Skill> s = repository.findById(id);
        return s.isPresent()
                ? ResponseEntity.ok().body(s.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    Skill update(@PathVariable(value = "id") Integer id, @RequestBody Skill skill) {
        return repository.findById(id) //
                .map(s -> {
                    s.setSkillableType(skill.getSkillableType());
                    s.setSkillableId(skill.getSkillableId());
                    s.setTechno(skill.getTechno());
                    s.setLevel(skill.getLevel());
                    s.setExperience(skill.getExperience());
                    return repository.save(s);
                }) //
                .orElseGet(() -> {
                    skill.setId(id);
                    return repository.save(skill);
                });
    }

    @DeleteMapping("/{id}")
    void destroy(@PathVariable(value = "id") Integer id) {
        repository.deleteById(id);
    }
}
