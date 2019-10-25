package rs.hybridit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.hybridit.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
