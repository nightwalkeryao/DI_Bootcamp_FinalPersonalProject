package club.devs.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skills")
@Data
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, name = "skillable_type")
    private String skillableType;
    @Column(nullable = false, name = "skillable_id")
    private int skillableId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(/*table = "technologies",*/ referencedColumnName = "id", name = "techno_id")
    private Techno techno;
    @Column
    private String level;
    @Column
    private short experience;

    public Skill(int id) {
        this.id = id;
    }
}
