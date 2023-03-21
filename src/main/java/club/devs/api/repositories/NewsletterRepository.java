package club.devs.api.repositories;

import club.devs.api.models.Newsletter;
import org.springframework.data.repository.CrudRepository;

public interface NewsletterRepository extends CrudRepository<Newsletter, Integer> {
}
