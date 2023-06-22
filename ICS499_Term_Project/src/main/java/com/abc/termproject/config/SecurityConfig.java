package com.abc.termproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// This method is used to store user credentials. After connecting to the DB, we will need to read in the users
	// into this unless there is another way to do it. For the hard coded users, it works great
	@Bean
	public UserDetailsService user() {
		UserBuilder users = User.withDefaultPasswordEncoder();
		UserDetails user = users.username("user").password("user").roles("USER").build();
		UserDetails admin = users.username("admin").password("admin").roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	// This method is used for authentication of admin vs customer (user)
	// Prevents customers from accessing admin page, currently admin can access both but we could easily
	// delete the user privilege from admin in the method above
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/login").permitAll();
					auth.requestMatchers("/customer").hasRole("USER");
					auth.requestMatchers("/admin").hasRole("ADMIN");
					auth.anyRequest().authenticated();
				})
				.formLogin(Customizer.withDefaults())
				.build();

	}
}
