package rs.hybridit.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.auth.JwtAuthenticationRequest;
import rs.hybridit.dto.MessageDto;
import rs.hybridit.dto.UserDto;
import rs.hybridit.model.Admin;
import rs.hybridit.model.Librarian;
import rs.hybridit.model.User;
import rs.hybridit.model.UserRoleName;
import rs.hybridit.model.UserTokenState;
import rs.hybridit.security.TokenHelper;
import rs.hybridit.serviceImpl.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

	private TokenHelper tokenUtils;

	private CustomUserDetailsService userDetailsService;

	private AuthenticationManager authenticationManager;

	public AuthenticationController(TokenHelper tokenUtils, CustomUserDetailsService customUserDetailsService,
		AuthenticationManager authenticationManager) {
		this.tokenUtils = tokenUtils;
		this.userDetailsService = customUserDetailsService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping(value = "registerAdmin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> registerAdmin(@RequestBody UserDto user) {
		if (this.userDetailsService.usernameTaken(user.getUsername())) {
			return new ResponseEntity<>(new MessageDto("Username is already taken.", "Error"), HttpStatus.OK);
		}
		Admin admin = new Admin(user);
		admin.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		if (this.userDetailsService.saveUser(admin)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}

	@PostMapping(value = "/registerLibrarian")
	public ResponseEntity<?> registerLibrarian(@RequestBody UserDto user) {
		if (this.userDetailsService.usernameTaken(user.getUsername())) {
			return new ResponseEntity<>(new MessageDto("Username is already taken.", "Error"), HttpStatus.OK);
		}
		Librarian librarian = new Librarian(user);
		librarian.setPassword(user.getPassword());
		librarian.setPassword(this.userDetailsService.encodePassword(user.getPassword()));
		if (this.userDetailsService.saveUser(librarian)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
		HttpServletResponse response) throws AuthenticationException, IOException {

		final Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(new MessageDto("Wrong username or password.", "Error"), HttpStatus.OK);
		} catch (DisabledException e) {
			return new ResponseEntity<>(new MessageDto("Account is not verified. Check your email.", "Error"),
				HttpStatus.OK);
		}
		User user = (User) authentication.getPrincipal();

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		UserRoleName userType = null;

		if (user instanceof Admin) {
			userType = UserRoleName.ROLE_ADMIN;
		} else {
			userType = UserRoleName.ROLE_LIBRARIAN;
		}

		return new ResponseEntity<>(new UserTokenState(jwt, expiresIn, userType), HttpStatus.OK);
	}

}
