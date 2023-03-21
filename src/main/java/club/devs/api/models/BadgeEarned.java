package club.devs.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "badge_earned")
@Data
@NoArgsConstructor
public class BadgeEarned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Badge.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "badge_id", /*table = "badges",*/ referencedColumnName = "id", nullable = false)
    private Badge badge;
    @Column(name = "earner_type")
    private String earnerType;
    @Column(name = "earner_id")
    private int earnerId;
    @Column
    private int points = 0;
    @Column(name = "earned_at")
    @CreationTimestamp
    private Date earnedAt;
    @Column(name = "used_at")
    private Date usedAt;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Mentor.class)
    private Mentor mentor;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Mentored.class)
    private Mentored mentored;
}
