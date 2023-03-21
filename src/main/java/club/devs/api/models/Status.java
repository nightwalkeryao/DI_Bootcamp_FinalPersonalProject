package club.devs.api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "statuses")
@Data
@NoArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String color;

    @OneToMany(targetEntity = Mentor.class, fetch = FetchType.LAZY, mappedBy = "status")
    @JsonManagedReference
    private List<Mentor> mentors;

    @OneToMany(targetEntity = Mentored.class, fetch = FetchType.LAZY, mappedBy = "status")
    @JsonManagedReference
    private List<Mentored> mentored;

    @OneToMany(targetEntity = JobPost.class, fetch = FetchType.LAZY, mappedBy = "status")
    @JsonManagedReference
    private List<JobPost> jobPosts;

    public Status(int id) {
        this.id = id;
    }
}
