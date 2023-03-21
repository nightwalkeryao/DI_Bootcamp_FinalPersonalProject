package club.devs.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mentors")
@Data
@NoArgsConstructor
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(/*table = "users",*/ referencedColumnName = "id", name = "user_id")
    private User user;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true, name = "profile_picture")
    private String profilePicture;
    @Column
    private String city;
    @Column
    @Unsigned
    private float balance;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(/*table = "statuses",*/ referencedColumnName = "id", name = "status_id")
    @JsonBackReference
    private Status status;
    @Column(name = "created_at")
    @CreationTimestamp
    Date createdAt;

    @OneToMany(targetEntity = Mentored.class, fetch = FetchType.LAZY, mappedBy = "mentor", cascade = CascadeType.ALL)
    private List<Mentored> mentored;

    public Mentor(int id) {
        this.id = id;
    }
}
