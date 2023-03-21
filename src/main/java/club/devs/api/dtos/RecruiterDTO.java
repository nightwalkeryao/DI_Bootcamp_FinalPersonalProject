package club.devs.api.dtos;

import club.devs.api.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RecruiterDTO {
    private int id;
    private int user;
    private String name;
    private String company;
    private String profilePicture;
    private String phoneNumber;
    private String jobTitle;
}
