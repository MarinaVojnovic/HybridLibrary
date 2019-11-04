package rs.hybridit.serviceImpl;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.hybridit.dto.UserDto;
import rs.hybridit.model.Authority;
import rs.hybridit.model.Role;
import rs.hybridit.model.User;
import rs.hybridit.repository.UserRepository;
import rs.hybridit.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User findById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	public void registerAdmin(UserDto user) throws Exception {
		if (this.usernameTaken(user.getUsername())) {
			throw new IllegalArgumentException("Username is already taken.");
		}
		User admin = new User(user);
		admin.setAuthority(new Authority(Role.ADMIN));
		admin.setPassword(this.passwordEncoder.encode(user.getPassword()));
		create(admin);
	}

	public void registerLibrarian(UserDto user) {
		if (this.usernameTaken(user.getUsername())) {
			throw new IllegalArgumentException("Username is already taken.");
		}
		User librarian = new User(user);
		librarian.setAuthority(new Authority(Role.LIBRARIAN));
		librarian.setPassword(this.passwordEncoder.encode(user.getPassword()));
		create(librarian);
	}

	public boolean usernameTaken(String username) {
		User user = userRepository.findByUsername(username);
		return user != null;
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
	public User findUserByToken(String token) {
		return userRepository.findByToken(token);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

}
