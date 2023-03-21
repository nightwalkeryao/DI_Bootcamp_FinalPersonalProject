package club.devs.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "technologies")
@Data
@NoArgsConstructor
public class Techno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String logo;

    @OneToMany(mappedBy = "techno", targetEntity = Skill.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Skill> skills;
}
