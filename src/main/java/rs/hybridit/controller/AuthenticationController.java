package rs.hybridit.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
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
import rs.hybridit.model.User;
import rs.hybridit.model.Role;
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
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerAdmin(@RequestBody UserDto user) {
		if (this.userDetailsService.registerAdmin(user)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(new MessageDto("Username is already taken.", "Error"), HttpStatus.OK);
	}

	@PostMapping(value = "/registerLibrarian")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerLibrarian(@RequestBody UserDto user) {
		if (this.userDetailsService.registerLibrarian(user)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(new MessageDto("Username is already taken.", "Error"), HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
		HttpServletResponse response) throws AuthenticationException, IOException {

		UserTokenState userTokenState = userDetailsService.login(authenticationRequest);
		if (userTokenState == null) {
			return new ResponseEntity<>(new MessageDto("Wrong username or password.", "Error"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userTokenState, HttpStatus.OK);
		}
	}

}
