package club.devs.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "recommendations")
@Data
@NoArgsConstructor
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @ManyToOne(targetEntity = Mentor.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(/*table = "mentors",*/ name = "mentor_id", referencedColumnName = "id", nullable = false)
    private Mentor mentor;
    @ManyToOne(targetEntity = Mentored.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(/*table = "mentored",*/ name = "mentored_id", referencedColumnName = "id", nullable = false)
    private Mentored mentored;
    @ManyToOne(targetEntity = JobPost.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(/*table = "job_posts",*/ name = "job_post_id", referencedColumnName = "id", nullable = false)
    private JobPost jobPost;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
}
