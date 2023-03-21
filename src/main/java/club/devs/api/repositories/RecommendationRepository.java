package club.devs.api.repositories;

import club.devs.api.models.Recommendation;
import org.springframework.data.repository.CrudRepository;

public interface RecommendationRepository extends CrudRepository<Recommendation, Integer> {
}
