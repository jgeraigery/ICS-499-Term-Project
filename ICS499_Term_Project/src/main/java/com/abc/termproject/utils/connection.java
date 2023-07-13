package com.abc.termproject.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import java.sql.Connection;
import java.sql.DriverManager;

public class connection {

	public Connection connection;
	
	 public boolean connect() {
	        try {
				//Each user will need to enter their own username and password for the database
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EZDB", "root", "Strangerdanger");
	            return true;
	        } catch (Exception ex) {
	            System.out.println("error - database did not connect\n" + ex.getMessage());			}
	        return false;
	    }
	
	public List<UserDetails> getUsers() {
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
	
	public void getInvoices() {
		//TODO
	}
	
	public void getDeliveries() {
		//TODO
	}
}
