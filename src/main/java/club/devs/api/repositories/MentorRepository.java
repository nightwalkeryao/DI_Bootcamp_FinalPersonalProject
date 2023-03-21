package club.devs.api.repositories;

import club.devs.api.models.Mentor;
import org.springframework.data.repository.CrudRepository;

public interface MentorRepository extends CrudRepository<Mentor, Integer> {
}
