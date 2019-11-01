package rs.hybridit.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.WebSecurityConfig;
import rs.hybridit.auth.JwtAuthenticationRequest;
import rs.hybridit.dto.MessageDto;
import rs.hybridit.dto.UserDto;
import rs.hybridit.model.UserTokenState;
import rs.hybridit.security.TokenHelper;
import rs.hybridit.service.UserService;
import rs.hybridit.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class AuthenticationController {

	private TokenHelper tokenUtils;

	private WebSecurityConfig webSecurityConfig;

	private UserService userService;

	@PostMapping(value = "/registerAdmin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerAdmin(@RequestBody UserDto user) {

		try{
			this.userService.registerAdmin(user);
			return new ResponseEntity<>(new MessageDto("Username is already taken.", "Error"), HttpStatus.CONFLICT);
		}catch(Exception e){
			return new ResponseEntity<>(new MessageDto(e.getMessage(), "Error"), HttpStatus.CONFLICT);
		}
	}


	@PostMapping(value = "/registerLibrarian")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerLibrarian(@RequestBody UserDto user) {

		try{
			this.userService.registerLibrarian(user);
			return new ResponseEntity<>(true, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(new MessageDto(e.getMessage(), "Error"), HttpStatus.CONFLICT);
		}
	}


	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
		HttpServletResponse response) throws AuthenticationException, IOException, Exception {

		UserTokenState userTokenState = webSecurityConfig.login(authenticationRequest);
		if (userTokenState == null) {
			return new ResponseEntity<>(new MessageDto("Wrong username or password.", "Error"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(userTokenState, HttpStatus.OK);
		}
	}

}
