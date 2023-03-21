package club.devs.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 64, nullable = false)
    private String username;
    @Column(length = 64, unique = true, nullable = false)
    private String email;
    @Column(length = 250, nullable = false)
    private String password;
    @Column(length = 250, nullable = false)
    private String token;
    @ManyToOne(targetEntity = Role.class, optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(/*table = "roles",*/ name = "role_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Role role;

    public User(int id) {
        this.id = id;
    }
}
