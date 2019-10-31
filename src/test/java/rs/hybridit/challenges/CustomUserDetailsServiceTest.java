package rs.hybridit.challenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.hybridit.auth.JwtAuthenticationRequest;
import rs.hybridit.dto.UserDto;
import rs.hybridit.model.Admin;
import rs.hybridit.model.Librarian;
import rs.hybridit.model.Role;
import rs.hybridit.model.User;
import rs.hybridit.model.UserTokenState;
import rs.hybridit.repository.UserRepository;
import rs.hybridit.security.TokenHelper;
import rs.hybridit.serviceImpl.CustomUserDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceTest {

	@InjectMocks
	CustomUserDetailsService customUserDetailsService;

	@Mock
	UserRepository userRepository;

	@Mock
	PasswordEncoder passwordEncoder;

	@Mock
	AuthenticationManager authenticationManager;

	@Mock
	Authentication authentication;

	@Mock
	SecurityContext securityContext;

	@Mock
	TokenHelper tokenUtils;

	@Test
	public void saveUser_UserDoesNotExist() {
		User u = new User();
		u.setId(1L);
		when(userRepository.save(u)).thenReturn(u);
		Boolean userSaved = customUserDetailsService.saveUser(u);
		verify(userRepository).save(u);
		Assert.assertTrue(userSaved);
	}

	@Test
	public void usernameTaken_usernameIsTaken() {
		User u = new User();
		u.setUsername("marina");
		when(userRepository.findByUsername("marina")).thenReturn(u);
		Boolean usernameTaken = customUserDetailsService.usernameTaken("marina");
		verify(userRepository).findByUsername("marina");
		Assert.assertTrue(usernameTaken);
	}

	@Test
	public void usernameTaken_usernameIsNotTaken() {
		Boolean usernameTaken = customUserDetailsService.usernameTaken("marina");
		verify(userRepository).findByUsername("marina");
		Assert.assertFalse(usernameTaken);
	}

	@Test
	public void findByUsername_userExists() {
		User u = new User();
		u.setUsername("marina");
		when(userRepository.findByUsername("marina")).thenReturn(u);
		User user = customUserDetailsService.loadUserByUsername("marina");
		verify(userRepository).findByUsername("marina");
		assertEquals("marina", user.getUsername());
	}

	@Test
	public void findByUsername_userDoesNotExist() {
		User user = customUserDetailsService.loadUserByUsername("marina");
		verify(userRepository).findByUsername("marina");
		assertEquals(null, user);
	}

	@Test
	public void loadUserById_ExistingIdGiven_ShouldBeSuccessfull() {
		User u = new User();
		u.setId(1L);
		when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(u));
		User user = customUserDetailsService.loadUserById(1L);
		verify(userRepository).findById(1L);
		assertEquals(1L, user.getId().longValue());
	}

	@Test
	public void loadUserById_NonExistingIdGiven_ShouldReturnNull() {
		User user = customUserDetailsService.loadUserById(1L);
		verify(userRepository).findById(1L);
		assertEquals(null, user);
	}

	@Test
	public void registerAdmin_existingUsernameGiven() {
		User registeredUser = new User();
		registeredUser.setUsername("marina");
		UserDto user = new UserDto();
		user.setUsername("marina");
		user.setPassword("password");
		when(userRepository.findByUsername("marina")).thenReturn(registeredUser);
		Boolean userRegistered = customUserDetailsService.registerAdmin(user);
		Assert.assertFalse(userRegistered);
	}

	@Test
	public void registerLibrarian_existingUsernameGiven() {
		User registeredUser = new User();
		registeredUser.setUsername("marina");
		UserDto user = new UserDto();
		user.setUsername("marina");
		user.setPassword("password");
		when(userRepository.findByUsername("marina")).thenReturn(registeredUser);
		Boolean userRegistered = customUserDetailsService.registerLibrarian(user);
		Assert.assertFalse(userRegistered);
	}

	@Test
	public void registerAdmin_uniqueUsernameGiven() {
		UserDto user = new UserDto();
		user.setUsername("marina");
		user.setPassword("password");
		Boolean userRegistered = customUserDetailsService.registerAdmin(user);
		Assert.assertTrue(userRegistered);
	}

	@Test
	public void registerLibrarian_uniqueUsernameGiven() {
		UserDto user = new UserDto();
		user.setUsername("marina");
		user.setPassword("password");
		Boolean userRegistered = customUserDetailsService.registerLibrarian(user);
		Assert.assertTrue(userRegistered);
	}

	@Test
	public void encodePasswsord() {
		String somePassword = "password";
		String encodedPassword = passwordEncoder.encode(somePassword);
		when(passwordEncoder.encode(somePassword)).thenReturn(encodedPassword);
		String returnedEncodedPassword = customUserDetailsService.encodePassword(somePassword);
		assertEquals(encodedPassword, returnedEncodedPassword);
	}

	@Test
	public void changePassword() {
		User user = new User();
		user.setUsername("marina");
		user.setPassword("password");
		String newPassword = "newPassword";
		String encodedPassword = passwordEncoder.encode(newPassword);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		when(authentication.getName()).thenReturn("marina");
		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
			.thenReturn(authentication);
		when(userRepository.findByUsername("marina")).thenReturn(user);
		when(passwordEncoder.encode(newPassword)).thenReturn(encodedPassword);
		User returnedUser = customUserDetailsService.changePassword(user.getPassword(), newPassword);
		assertEquals(encodedPassword, returnedUser.getPassword());
	}


	@Test
	public void loginAdmin_successfull() {
		Admin user = new Admin();
		Integer expiresIn = 87600;
		user.setUsername("marina");
		user.setPassword("password");
		String jwtToken = tokenUtils.generateToken(user.getUsername());
		JwtAuthenticationRequest authenticationRequest = new JwtAuthenticationRequest(user.getUsername(),
			user.getPassword());
		SecurityContextHolder.setContext(securityContext);
		when(authentication.getPrincipal()).thenReturn(user);
		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
			.thenReturn(authentication);
		when(tokenUtils.generateToken(user.getUsername())).thenReturn(jwtToken);
		when(tokenUtils.getExpiredIn()).thenReturn(expiresIn);
		UserTokenState userTokenState = customUserDetailsService.login(authenticationRequest);
		assertEquals(jwtToken, userTokenState.getAccessToken());
		assertEquals(expiresIn.longValue(), userTokenState.getExpiresIn().longValue());
		assertEquals(Role.ADMIN, userTokenState.getUserRoleName());
	}

	@Test
	public void loginLibrarian_successfull() {
		Librarian user = new Librarian();
		Integer expiresIn = 87600;
		user.setUsername("marina");
		user.setPassword("password");
		String jwtToken = tokenUtils.generateToken(user.getUsername());
		JwtAuthenticationRequest authenticationRequest = new JwtAuthenticationRequest(user.getUsername(),
			user.getPassword());
		SecurityContextHolder.setContext(securityContext);
		when(authentication.getPrincipal()).thenReturn(user);
		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
			.thenReturn(authentication);
		when(tokenUtils.generateToken(user.getUsername())).thenReturn(jwtToken);
		when(tokenUtils.getExpiredIn()).thenReturn(expiresIn);
		UserTokenState userTokenState = customUserDetailsService.login(authenticationRequest);
		assertEquals(jwtToken, userTokenState.getAccessToken());
		assertEquals(expiresIn.longValue(), userTokenState.getExpiresIn().longValue());
		assertEquals(Role.LIBRARIAN, userTokenState.getUserRoleName());
	}

	@Test
	public void loginAdmin_badCredentials() {
		Admin user = new Admin();
		Integer expiresIn = 87600;
		user.setUsername("marina");
		user.setPassword("password");
		String jwtToken = tokenUtils.generateToken(user.getUsername());
		JwtAuthenticationRequest authenticationRequest = new JwtAuthenticationRequest(user.getUsername(),
			user.getPassword());
		SecurityContextHolder.setContext(securityContext);
		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
			.thenThrow(BadCredentialsException.class);
		UserTokenState userTokenState = customUserDetailsService.login(authenticationRequest);
		assertNull(userTokenState);
	}

}
