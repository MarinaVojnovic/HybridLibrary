package rs.hybridit.service;

import java.util.List;
import rs.hybridit.model.User;

public interface UserService {

	User findById(long id);

	List<User> getAll();

	User create(User user);

	void delete(User user);

	User findByUsername(String username);

	User findUserByToken(String token);

}
