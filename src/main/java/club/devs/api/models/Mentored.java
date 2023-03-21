package club.devs.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "mentored")
@Data
@NoArgsConstructor
public class Mentored {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(/*table = "users",*/ name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne(targetEntity = Mentor.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(/*table = "mentors",*/ name = "mentor_id", referencedColumnName = "id", nullable = true)
    private Mentor mentor;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true, name = "profile_picture")
    private String profilePicture;
    @Column
    private String city;
    @Column
    @Unsigned
    private float balance;
    @ManyToOne(targetEntity = Status.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(/*table = "statuses",*/ name = "status_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Status status;
    @Column(name = "created_at")
    @CreationTimestamp
    Date createdAt;

    public Mentored(int id) {
        this.id = id;
    }
}
