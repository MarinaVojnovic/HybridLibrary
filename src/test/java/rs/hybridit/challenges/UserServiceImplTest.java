package rs.hybridit.challenges;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.hybridit.dto.UserDto;
import rs.hybridit.model.User;
import rs.hybridit.repository.UserRepository;
import rs.hybridit.serviceImpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Mock
	PasswordEncoder passwordEncoder;

	@Test
	public void findOne_ExistingIdGiven_ShouldBeSuccessfull() {
		User u = new User();
		u.setId(1L);
		when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(u));
		User user = userService.findById(1L);
		verify(userRepository).findById(1L);
		assertEquals(1L, user.getId().longValue());
	}

	@Test
	public void findOne_NonExistingIdGiven_ShouldReturnNull() {
		User user = userService.findById(1L);
		verify(userRepository).findById(1L);
		assertEquals(null, user);
	}

	@Test
	public void getAll_UsersExist() {
		User user = new User();
		List<User> users = new ArrayList<>();
		users.add(user);
		when(userRepository.findAll()).thenReturn(users);
		List<User> returnedUsers = userService.getAll();
		verify(userRepository).findAll();
		assertEquals(users, returnedUsers);
	}

	@Test
	public void getAll_UsersDoNotExist() {
		List<User> users = new ArrayList<User>();
		List<User> returnedUsers = userService.getAll();
		verify(userRepository).findAll();
		assertEquals(users, returnedUsers);
	}

	@Test
	public void create_UserDoesNotExist() {
		User u = new User();
		u.setId(1L);
		when(userRepository.save(u)).thenReturn(u);
		User user = userService.create(u);
		verify(userRepository).save(u);
		assertEquals(u, user);
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerAdmin_existingUsernameGiven() throws Exception {

		User registeredUser = new User();
		registeredUser.setUsername("marina");
		UserDto user = new UserDto();
		user.setUsername("marina");
		user.setPassword("password");
		when(userRepository.findByUsername("marina")).thenReturn(registeredUser);
		userService.registerAdmin(user);
		/*
		Exception result = userService.registerAdmin(user);
		Assert.assertFalse(result);

		 */

	}

	@Test(expected = IllegalArgumentException.class)
	public void registerLibrarian_existingUsernameGiven() {
		User registeredUser = new User();
		registeredUser.setUsername("marina");
		UserDto user = new UserDto();
		user.setUsername("marina");
		user.setPassword("password");
		when(userRepository.findByUsername("marina")).thenReturn(registeredUser);
		userService.registerLibrarian(user);
	}

	@Test
	public void registerAdmin_uniqueUsernameGiven() throws Exception {

		UserDto user = new UserDto();
		user.setUsername("marina");
		user.setPassword("password");
		userService.registerAdmin(user);
	}

	@Test
	public void registerLibrarian_uniqueUsernameGiven() {

		UserDto user = new UserDto();
		user.setUsername("marina");
		user.setPassword("password");
		userService.registerLibrarian(user);

	}

}
