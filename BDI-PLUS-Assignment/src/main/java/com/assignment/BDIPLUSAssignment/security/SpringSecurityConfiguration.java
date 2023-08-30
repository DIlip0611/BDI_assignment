package com.assignment.BDIPLUSAssignment.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	@Bean
	public InMemoryUserDetailsManager createUserDetails() {
		UserDetails userDetails1 = CreateNewUser("Dilip", "Dilip@123");
		UserDetails userDetails2 = CreateNewUser("Rahul", "Rahul0611");
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	private UserDetails CreateNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password)
				.roles("USER", "ADMIN").build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	All Urls are protected
//	a login form is shown for unauthorised requests
//	csrf disablr
//	frames
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());

		http.csrf().disable();
		http.headers().frameOptions().disable();

		return http.build();

	}
}
