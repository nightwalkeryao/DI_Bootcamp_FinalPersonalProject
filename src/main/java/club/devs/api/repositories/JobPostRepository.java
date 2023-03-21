package club.devs.api.repositories;

import club.devs.api.models.JobPost;
import org.springframework.data.repository.CrudRepository;

public interface JobPostRepository extends CrudRepository<JobPost, Integer> {
}
