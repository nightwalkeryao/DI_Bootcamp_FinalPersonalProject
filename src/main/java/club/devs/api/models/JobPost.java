package club.devs.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "job_posts")
@Data
@NoArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column(name = "contract_type")
    private String contractType;
    @Column(length = 5000)
    private String description;
    @Column
    private String company;
    @ManyToOne(targetEntity = Recruiter.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(/*table = "recruiters",*/ name = "recruiter_id", referencedColumnName = "id", nullable = false)
    private Recruiter recruiter;
    @ManyToOne(targetEntity = Status.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(/*table = "statuses", */ name = "status_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Status status;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
}
