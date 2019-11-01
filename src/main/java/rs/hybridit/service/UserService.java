package rs.hybridit.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import rs.hybridit.dto.UserDto;
import rs.hybridit.model.User;

public interface UserService extends UserDetailsService {

	User findById(long id);

	List<User> getAll();

	User create(User user);

	void delete(User user);

	User findUserByToken(String token);

	Boolean registerLibrarian(UserDto user);

	Boolean registerAdmin(UserDto user);

}
