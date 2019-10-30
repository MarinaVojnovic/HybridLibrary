package rs.hybridit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import rs.hybridit.security.TokenHelper;
import rs.hybridit.auth.RestAuthenticationEntryPoint;
import rs.hybridit.auth.TokenAuthenticationFilter;
import rs.hybridit.serviceImpl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	Impelementation of PasswordEncoder using BCrypt hashing function.
	BCrypt does 10 rounds of hashing by default.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	//Unauthorized access to protected resources
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//Defining way of autentification
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Autowired
	TokenHelper tokenUtils;

	// Defining access rights to ceratin URLs
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			// communication between client and server is stateless
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

			// for unauthorized requests send 401 error
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

			.authorizeRequests()
			.antMatchers("/auth/login").permitAll()
			.antMatchers("/auth/registerLibrarian").permitAll()
			.antMatchers("/**").permitAll()

			// every request needs to be authorized
			.anyRequest().authenticated().and()

			// add filter before every request
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
				BasicAuthenticationFilter.class);

		http.csrf().disable();


	}

	// General safety of application
	@Override

	public void configure(WebSecurity web) throws Exception {
		// Token Filter will ignore these paths
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login", "/h2/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/login", "/h2/**", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
	}

}
