package com.abc.termproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import java.util.List;
import com.abc.termproject.utils.DatabaseUtility;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

	/**
	 * This method creates a user list from the database in order to validate users.
	 * @return (UserDetailsService) - user list
	 */
	@Bean
	public UserDetailsService user() {
		DatabaseUtility connection = new DatabaseUtility();
		List <UserDetails> userList = connection.getUsers();
		return new InMemoryUserDetailsManager(userList);
	}
	
	/**
	 * Method used for adjusting the security filter chain. Used for authorizing users
	 * to do different things, such as page access and access to post methods.
	 * @param (HttpSecurity) - http
	 * @return (SecurityFilterChain) - http
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		System.out.println();
        return http
            .authorizeHttpRequests(auth -> {
            auth.requestMatchers("/login").permitAll();
            auth.requestMatchers("/customer").hasRole("USER");
            auth.requestMatchers("/driver").hasRole("DRIVER");
            auth.requestMatchers("/admin").hasRole("ADMIN");
            auth.anyRequest().authenticated();
            })
            .csrf(csrf -> csrf.disable())
            .formLogin(Customizer.withDefaults())
            .build();

	}
	
}
