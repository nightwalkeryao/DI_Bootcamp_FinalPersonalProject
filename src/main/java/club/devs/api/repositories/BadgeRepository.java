package club.devs.api.repositories;

import club.devs.api.models.Badge;
import org.springframework.data.repository.CrudRepository;

public interface BadgeRepository extends CrudRepository<Badge, Integer> {
}
