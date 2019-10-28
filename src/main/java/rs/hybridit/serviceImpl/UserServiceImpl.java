package rs.hybridit.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import rs.hybridit.model.User;
import rs.hybridit.repository.UserRepository;
import rs.hybridit.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
