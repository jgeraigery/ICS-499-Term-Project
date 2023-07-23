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
	

	// This method is used to store user credentials. After connecting to the DB, we will need to read in the users
	// into this unless there is another way to do it. For the hard coded users, it works great
	@Bean
	public UserDetailsService user() {
		DatabaseUtility connection = new DatabaseUtility();
//		connection.connect();
		List <UserDetails> userList = connection.getUsers();
		return new InMemoryUserDetailsManager(userList);
	}
	
	// This method is used for authentication of admin vs customer vs driver
	// also disables csrf to allow for calling post methods
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
