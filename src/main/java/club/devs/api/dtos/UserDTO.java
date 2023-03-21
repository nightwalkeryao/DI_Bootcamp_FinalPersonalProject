package club.devs.api.dtos;

import club.devs.api.models.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
    private int id;
    private String username;
    private String email;
    private String password;
    private String token;
    private int role;
}
