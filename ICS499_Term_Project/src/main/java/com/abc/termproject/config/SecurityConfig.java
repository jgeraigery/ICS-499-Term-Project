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
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    public Connection connection;
	
	
	//method checks if the database can be connected to
    private boolean connect() {
        try {
			//Each user will need to enter their own username and password for the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EZDB", "root", "Strangerdanger");
            return true;
        } catch (Exception ex) {
            System.out.println("error - database did not connect\n" + ex.getMessage());			}
        return false;
    }

	// This method is used to store user credentials. After connecting to the DB, we will need to read in the users
	// into this unless there is another way to do it. For the hard coded users, it works great
	@Bean
	public UserDetailsService user() {
		connect();
		List <UserDetails> userList = getUsers();
		return new InMemoryUserDetailsManager(userList);
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		UserDetails user = users.username("user").password("user").roles("USER").build();
//		UserDetails admin = users.username("admin").password("admin").roles("USER", "ADMIN").build();
//		return new InMemoryUserDetailsManager(user, admin);
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
	
	private List<UserDetails> getUsers() {
	    List<UserDetails> userList = new ArrayList<UserDetails>();
	    UserBuilder users = User.withDefaultPasswordEncoder();

	    try {
	        String query = "SELECT * FROM user";
	        java.sql.PreparedStatement statement = connection.prepareStatement(query);
	        java.sql.ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            userList.add(users.username(resultSet.getString("userName"))
	            .password(resultSet.getString("password"))
	            .roles(resultSet.getString("userType"))
	            .build());

	        }
	    } catch (Exception ex) {
	        System.out.println("error - could not check username and password\n" + ex.getMessage());
	    }
	    return userList;
	}
}
