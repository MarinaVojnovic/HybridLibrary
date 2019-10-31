package rs.hybridit.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.dto.UserDto;
import rs.hybridit.model.User;
import rs.hybridit.service.UserService;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> create(@RequestBody @Valid UserDto userDto) {
		User user = userService.create(new User(userDto));
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {
		User user = userService.findById(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("User with given id does not exist");
		}
	}

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.getAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
		User user = userService.findById(id);
		if (user != null) {
			user.setName(userDto.getName());
			user.setLastName(userDto.getLastName());
			user.setUsername(userDto.getUsername());
			user.setPassword(userDto.getPassword());
			user.setEmail(userDto.getEmail());
			user.setBookCopies(userDto.getBookCopies());
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("User with given id does not exist");
		}
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		User user = userService.findById(id);
		if (user != null) {
			userService.delete(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("User with given id does not exist");
		}
	}

}
