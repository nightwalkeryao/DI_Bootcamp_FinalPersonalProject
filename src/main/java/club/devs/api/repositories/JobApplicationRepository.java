package club.devs.api.repositories;

import club.devs.api.models.JobApplication;
import org.springframework.data.repository.CrudRepository;

public interface JobApplicationRepository extends CrudRepository<JobApplication, Integer> {
}
