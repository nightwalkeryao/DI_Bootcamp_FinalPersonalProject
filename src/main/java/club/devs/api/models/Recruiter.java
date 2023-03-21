package club.devs.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "recruiters")
@Data
@NoArgsConstructor
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(/*table = "users",*/ referencedColumnName = "id", name = "user_id")
    private User user;
    @Column(nullable = false)
    private String name;
    @Column
    private String company;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    public Recruiter(int id) {
        this.id = id;
    }
}
