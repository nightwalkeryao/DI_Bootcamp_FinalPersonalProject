package club.devs.api.repositories;

import club.devs.api.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM users WHERE username=:username AND password=:password LIMIT 1", nativeQuery = true)
    Optional<User> findByCredentials(String username, String password);
}
