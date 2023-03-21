package club.devs.api.dtos;

import club.devs.api.models.Status;
import club.devs.api.models.User;
import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MentorDTO {
    private int id;
    private int user;
    private String name;
    private String profilePicture;
    private String city;
    private float balance;
    private int status;
}
