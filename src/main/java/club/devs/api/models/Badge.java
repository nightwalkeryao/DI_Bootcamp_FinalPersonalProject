package club.devs.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "badges")
@Data
@NoArgsConstructor
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String image;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = BadgeEarned.class, mappedBy = "badge")
    private List<BadgeEarned> earnings;
}
