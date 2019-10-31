package rs.hybridit.serviceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rs.hybridit.auth.JwtAuthenticationRequest;
import rs.hybridit.dto.MessageDto;
import rs.hybridit.dto.UserDto;
import rs.hybridit.model.Admin;
import rs.hybridit.model.Librarian;
import rs.hybridit.model.Role;
import rs.hybridit.model.User;
import rs.hybridit.model.UserTokenState;
import rs.hybridit.repository.UserRepository;
import rs.hybridit.security.TokenHelper;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	protected final Log LOGGER = LogFactory.getLog(getClass());

	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	private AuthenticationManager authenticationManager;

	private TokenHelper tokenUtils;

	public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder,
		AuthenticationManager authenticationManager, TokenHelper tokenUtils) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.tokenUtils = tokenUtils;

	}

	public UserTokenState login(JwtAuthenticationRequest authenticationRequest) {
		final Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return null;
		}

		User user = (User) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		Role role = null;

		if (user instanceof Admin) {
			role = Role.ADMIN;
		} else {
			role = Role.LIBRARIAN;
		}

		return new UserTokenState(jwt, expiresIn, role);
	}

	public boolean registerAdmin(UserDto user) {
		if (this.usernameTaken(user.getUsername())) {
			return false;
		}
		Admin admin = new Admin(user);
		admin.setPassword(this.encodePassword(user.getPassword()));
		return saveUser(admin);
	}

	public boolean registerLibrarian(UserDto user) {
		if (this.usernameTaken(user.getUsername())) {
			return false;
		}
		Librarian librarian = new Librarian(user);
		librarian.setPassword(this.encodePassword(user.getPassword()));
		return saveUser(librarian);
	}

	public boolean saveUser(User user) {
		try {
			this.userRepository.save(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean usernameTaken(String username) {
		User user = userRepository.findByUsername(username);
		return user != null;
	}

	@Override
	public User loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}

	public User loadUserById(long id) {
		User user = userRepository.findById(id).orElse(null);
		return user;
	}

	public String encodePassword(String password) {
		return this.passwordEncoder.encode(password);
	}

	public User changePassword(String oldPassword, String newPassword) {

		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		if (authenticationManager != null) {
			LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		} else {
			LOGGER.debug("No authentication manager set. can't change Password!");

			return null;
		}

		LOGGER.debug("Changing password for user '" + username + "'");

		User user = (User) loadUserByUsername(username);

		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		return user;
	}

}
