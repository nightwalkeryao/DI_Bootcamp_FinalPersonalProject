package club.devs.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "job_applications")
@Data
@NoArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "applicant_type")
    private String applicantType;
    @Column(name = "applicant_id")
    private String applicantId;
    @ManyToOne(targetEntity = JobPost.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "job_post_id", /*table = "job_posts",*/ referencedColumnName = "id", nullable = false)
    private JobPost jobPost;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
}
