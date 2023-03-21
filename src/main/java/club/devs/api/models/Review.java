package club.devs.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Polymorphism;

import java.util.Date;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, name = "reviewer_type")
    private String reviewerType;
    @Column(nullable = false, name = "reviewer_id")
    private int reviewerId;
    @Column(nullable = false)
    private short rating;
    @Column
    private String description;
    @Column(nullable = false, name = "created_at")
    @CreationTimestamp
    private Date createdAt;
}
