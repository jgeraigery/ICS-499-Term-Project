package com.abc.termproject.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.abc.termproject.entity.Invoice;
import com.abc.termproject.entity.InvoiceItem;

import org.springframework.security.core.userdetails.User.UserBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseUtility {

	public Connection connection;
	
	/**
	 * Method to establish DB connection. Must be called before and closed after any DB query.
	 * @return (boolean) 
	 */
	public boolean connect() {
	        try {
				//Each user will need to enter their own username and password for the database
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EZDB", "root", "Strangerdanger");
	            // Thomas's connection
//	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EZDB", "root", "Quintav85$311");
	            return true;
	        } catch (Exception ex) {
	            System.out.println("error - database did not connect\n" + ex.getMessage());			}
	        return false;
	    }
	
	/**
	 * Method is used to populate Springs login credential table.
	 * @return (List<UserDetails>) - list of UserDetail objects defined springs security framework
	 */
	public List<UserDetails> getUsers() {
	    List<UserDetails> userList = new ArrayList<UserDetails>();
	    UserBuilder users = User.withDefaultPasswordEncoder();

	    try {
	        String query = "SELECT * FROM user";
	        PreparedStatement statement = connection.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            userList.add(users.username(resultSet.getString("userName"))
	            .password(resultSet.getString("password"))
	            .roles(resultSet.getString("userType"))
	            .build());
	        }
	        connection.close();
	    } catch (Exception ex) {
	        System.out.println("error - could not check username and password\n" + ex.getMessage());
	    }
	    return userList;
	}
	
	public void getInvoices(int custID, String date) {
		//TODO
	}
	
	/**
	 * Method calls DB to query an invoice by selected date. DB returns a series of DB entries that are
	 * that are used to create InvoiceItem objects that are added to a list. That list will be added to
	 * an Invoice object that it returned.
	 * @param (String) - date; YYYY-MM-DD format is required to function correctly
	 * @param (int) - id; user id is used to get name to be added to Invoice object
	 * @return (Invoice) - invoice; Object that contains all relevant data for a particular invoice.
	 */
	public Invoice getInvoice(String date, int id) {
		String fullName = getUserFullNameByID(id);
		List<InvoiceItem> items = new ArrayList<InvoiceItem>();
		String query = "select * from user natural join InvoiceItem natural join product "
				+ "where invoiceDate = ? && userID = ?";
		try {
		connect();
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, date);
		stmt.setInt(2, id);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			InvoiceItem ii = new InvoiceItem(rs.getInt("productID"), 
					rs.getString("productName"), 
					rs.getString("description"), rs.getInt("price"), rs.getInt("quantity"));
			items.add(ii);
		}
		//TODO create new invoice item to add list to, return invoice object
		Invoice invoice = new Invoice(fullName, id);
		invoice.setItemList(items);
		connection.close();
		return invoice;
		} catch (Exception ex) {
	        System.out.println(ex.getMessage());
		}
		return null;
		
	}
	
	/**
	 * Method to retrieve users full name by user id
	 * @param (int) id - users numeric auto generated id
	 * @return (String) - Users full first and last name
	 */
	public String getUserFullNameByID(int id) {
		String fullName = "";
		try { connect();
	        String query = "SELECT * FROM user where userID= " + '"'+id+'"';
	        PreparedStatement statement = connection.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            fullName = fullName + resultSet.getString("firstName") + " " + resultSet.getString("lastName");

	        }
	        connection.close();
	        return fullName;
	    } catch (Exception ex) {
	        System.out.println("error - could not check username and password\n" + ex.getMessage());
	    }
	    return null;
	}

	
	
	public void getDeliveries(int driverID) {
		//TODO
	}
	
	public void getDelivery() {
		//TODO
	}
	
	//Returns a list of unique dates for the customer to choose from. 
	//This will be used to create an inventory populated by all items from that date.
	public List<String> getDatesInvoice() {
		//TODO
		return null;
	}
	
	
	public String getUserFullName(String user) {
		String fullName = "";
		try { connect();
	        String query = "SELECT * FROM user where userName= " + '"'+user+'"';
	        PreparedStatement statement = connection.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            fullName = fullName + resultSet.getString("firstName") + " " + resultSet.getString("lastName");

	        }
	        connection.close();
	    } catch (Exception ex) {
	        System.out.println("error - Could not retreive user name\n" + ex.getMessage());
	    }
	    return fullName;
	}
}
