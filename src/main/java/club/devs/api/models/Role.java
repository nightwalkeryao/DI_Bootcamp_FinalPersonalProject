package club.devs.api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 160, nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = User.class, mappedBy = "role")
    @JsonManagedReference
    private List<User> users;

//    public Role(int roleId) {
//        this.id = roleId;
//    }
}
