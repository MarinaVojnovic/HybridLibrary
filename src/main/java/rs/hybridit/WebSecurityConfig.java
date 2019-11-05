package rs.hybridit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import rs.hybridit.auth.JwtAuthenticationRequest;
import rs.hybridit.model.Role;
import rs.hybridit.model.User;
import rs.hybridit.model.UserTokenState;
import rs.hybridit.security.TokenHelper;
import rs.hybridit.auth.TokenAuthenticationFilter;
import rs.hybridit.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private TokenHelper tokenHelper;
	private PasswordEncoder passwordEncoder;
	private UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			// communication between client and server is stateless
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers("/auth/login").permitAll()
			// every request needs to be authorized
			.anyRequest().authenticated().and()
			// add filter before every request
			.addFilterBefore(new TokenAuthenticationFilter(tokenHelper, userService),
				BasicAuthenticationFilter.class);
		http.csrf().disable();

	}

	public void configure(WebSecurity web) throws Exception {
		// Token Filter will ignore these paths
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login", "/h2/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/login", "/h2/**", "/webjars/**", "/*.html", "/favicon.ico",
			"/**/*.html", "/**/*.css", "/**/*.js");

		web.ignoring().antMatchers(
			"/v2/api-docs",
			"/swagger-resources/configuration/ui",
			"/swagger-resources",
			"/swagger-resources/configuration/security",
			"/swagger-ui.html",
			"/webjars/**");
	}


	public UserTokenState login(JwtAuthenticationRequest authenticationRequest) throws Exception {
		final Authentication authentication;
		try {
			authentication = authenticationManagerBean().authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return null;
		}
		User user = (User) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenHelper.generateToken(user.getUsername());
		int expiresIn = tokenHelper.getExpiredIn();
		Role role = null;
		if (user.getAuthority().getRole().equals(Role.ADMIN)) {
			role = Role.ADMIN;
		} else {
			role = Role.LIBRARIAN;
		}
		return new UserTokenState(jwt, expiresIn, role);
	}

	public User changePassword(String oldPassword, String newPassword) throws Exception {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		authenticationManagerBean().authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		User user = (User) userService.loadUserByUsername(username);
		user.setPassword(passwordEncoder.encode(newPassword));
		userService.create(user);
		return user;
	}


}
