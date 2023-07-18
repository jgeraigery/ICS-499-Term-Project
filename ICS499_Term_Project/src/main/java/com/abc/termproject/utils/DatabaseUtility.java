package com.abc.termproject.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.abc.termproject.entity.DateInvoiceNumber;
import com.abc.termproject.entity.Delivery;
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
            
	            // Alexey's connection
              //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EZDB", "root", "ics311");
            
	        	  //Ahmad's Connection
	        	  //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EZDB", "root", "root");
            
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
	    	if(connect()) {
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
	    	}} catch (Exception ex) {
	        System.out.println("error - could not get user list\n" + ex.getMessage());
	    }
	    return userList;
	}
	
	/**
	 * Method returns a list of DateInvoiceNumberObjects for a user to select from. The returned object's 
	 * attributes are used by the getInvoice method to get details for a specific invoice.
	 * @param (String) - user; userName
	 * @return (List<DateInvoiceNumber>) - dateList; object with attributes userID, invoiceID, and invoiceDate
	 */
	public List<DateInvoiceNumber> getInvoiceDates(String user) {
		List<DateInvoiceNumber> dateList = new ArrayList<DateInvoiceNumber>();
		String query = "select distinct invoiceDate, invoiceID, userID from invoiceItem "
				+ "natural join user where userName = ?";
		try {
		if(connect()) {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				DateInvoiceNumber dateItem= new DateInvoiceNumber(rs.getInt("userID"), 
						rs.getInt("invoiceID"), 
						rs.getString("invoiceDate"));
				dateList.add(dateItem);
			}
			connection.close();
			return dateList;
		}} catch (Exception ex) {
	        System.out.println("error - could not get invoice dates\n" + ex.getMessage());
		}
		return null;
		
	}
	
	/**
	 * Method calls DB to query an invoice by selected date. DB returns a series of DB entries that are
	 * that are used to create InvoiceItem objects that are added to a list. That list will be added to
	 * an Invoice object that it returned.
	 * @param (String) - date; YYYY-MM-DD format is required to function correctly
	 * @param (int) - id; user id is used to get name to be added to Invoice object
	 * @param (int) - invoiceID; used to query database for items matching a specific invoice number
	 * allows for a customer to have multiple invoices from a single date
	 * @return (Invoice) - invoice; Object that contains all relevant data for a particular invoice.
	 */
	public Invoice getInvoice(String date, int id, int invoiceID) {
		String fullName = getUserFullNameByID(id);
		List<InvoiceItem> items = new ArrayList<InvoiceItem>();
		String query = "select * from user natural join InvoiceItem natural join product where invoiceID = ?";
		try {
		if(connect()) {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, invoiceID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				InvoiceItem ii = new InvoiceItem(rs.getInt("productID"), 
						rs.getString("productName"), 
						rs.getString("description"), rs.getInt("price"), rs.getInt("quantity"));
				items.add(ii);
			}
			Invoice invoice = new Invoice(fullName, id);
			invoice.setItemList(items);
			invoice.setInvoiceID(invoiceID);
			connection.close();
			return invoice;
		}} catch (Exception ex) {
	        System.out.println("error - could not get invoice\n" + ex.getMessage());
		}
		return null;
	}
	
	/**
	 * Method takes in driverID and date of deliveries to return a list of Delivery objects
	 * @param (int) - driverID; drivers unique id number
	 * @param (String) - date; date of delivers requested from the DB
	 * @return List<Delivery> - dateList; List of all delivery objects for specified date
	 */
	public List<Delivery> getDeliveries(int driverID, String date) {
		List<Delivery> dateList = new ArrayList<Delivery>();
		String query = "Select * from deliveries natural join invoiceItems where driverID = ? and date = ? and status = ?";
		try {
			if(connect()) {
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setInt(1, driverID);
				stmt.setString(2, date);
				stmt.setString(3, "in progress");
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Delivery dateItem= new Delivery(rs.getInt("deliveryID"), 
							rs.getInt("employeeID"), 
							rs.getString("invoiceDate"), rs.getInt("invoiceID"), rs.getString("status"));
					dateList.add(dateItem);
				}
				connection.close();
				return dateList;
			}} catch (Exception ex) {
	        System.out.println("error - could not retreive deliveries list" + ex.getMessage());
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
		try { 
			if(connect()) {
		        String query = "SELECT * FROM user where userID= ?";
		        PreparedStatement statement = connection.prepareStatement(query);
		        statement.setInt(1, id);
		        ResultSet resultSet = statement.executeQuery();
		        while (resultSet.next()) {
		            fullName = fullName + resultSet.getString("firstName") + " " + resultSet.getString("lastName");
	
		        }
		        connection.close();
		        return fullName;
			}} catch (Exception ex) {
	        System.out.println("error - could not get full name\n" + ex.getMessage());
	    }
	    return null;
	}
	
	/**
	 * Method to retrieve users full name by userName
	 * @param (String) user - user name
	 * @return (String) - Users full first and last name
	 */
	public String getUserFullName(String user) {
		String fullName = "";
		try { 
			if(connect()) {
		        String query = "SELECT * FROM user where userName = ?";
		        PreparedStatement statement = connection.prepareStatement(query);
		        statement.setString(1, user);
		        ResultSet resultSet = statement.executeQuery();
		        while (resultSet.next()) {
		            fullName = fullName + resultSet.getString("firstName") + " " + resultSet.getString("lastName");
	
		        }
		        connection.close();
		        return fullName;
			}} catch (Exception ex) {
	        System.out.println("error - Could not retreive user full name\n" + ex.getMessage());
	    }
	    return null;
	}
	
	/**
	 * Method to retrieve users ID by userName
	 * @param (String) user - user name
	 * @return (int) - UserID
	 */
	public int getUserIDByUserName(String userName) {
		int id = 0;
		try { 
			if(connect()) {
		        String query = "SELECT userID FROM user where userName = ? ";
		        PreparedStatement statement = connection.prepareStatement(query);
		        statement.setString(1, userName);
		        ResultSet resultSet = statement.executeQuery();
		        while (resultSet.next()) {
		            id = resultSet.getInt("userID");
		        }
		        connection.close();
		        return id;
			}} catch (Exception ex) {
	        System.out.println("error - could not check username\n" + ex.getMessage());
	    }
	    return id;
	}
	
	/**
	 * Used to get new invoiceID value for new invoices entered by admin
	 * @return (int) - id; maximum id number from db + 1
	 */
	public int getNewInvoiceID() {
		int id = 0;
		try { 
			if(connect()) {
		        String query = "Select MAX(invoiceID) as max from invoiceItem";
		        PreparedStatement statement = connection.prepareStatement(query);
		        ResultSet resultSet = statement.executeQuery();
		        while (resultSet.next()) {
		            id = resultSet.getInt("max");
		        }
		        connection.close();
		        return id+1;
			}} catch (Exception ex) {
	        System.out.println("error - could not generate new ID\n" + ex.getMessage());
	    }
	    return id;
	}
	
	/**
	 * Method for inserting new invoices into the connected database; itemID is auto-incremented by the DB
	 * and invoiceID is generated by the getNewInvoiceID in class method
	 * @param (int) - userID; unique userID
	 * @param (String) - invoiceDate; date of invoice YYYY-MM-DD format is required
	 * @param (int) - productID; unique productID
	 * @param (int) - quantity; quantity of item
	 * @return (Boolean) - returns true on successful insert, else false
	 */
	public Boolean insertInvoice(int userID, String invoiceDate, int productID, int quantity) {
		int invoiceID = getNewInvoiceID();
		try {
			if(connect()) {
				String insert = "Insert into invoiceItem (invoiceID, userID, invoiceDate, productID, quantity)"
						+ "values(?,?,?,?,?)";
		        PreparedStatement stmt = connection.prepareStatement(insert);
		        stmt.setInt(1, invoiceID);
				stmt.setInt(2, userID);
				stmt.setString(3, invoiceDate);
				stmt.setInt(4, productID);
				stmt.setInt(5, quantity);
				int row = stmt.executeUpdate();
	            System.out.println("Rows affected: " + row);//1
				connection.close();
				return true;
			}} catch (Exception ex) {
	        System.out.println("error - could not insert into DB see message\n" + ex.getMessage());
		}
		return false;
	}
	
	/**
	 * Method updates status field for a delivery in db to delivered
	 * @param (int) - deliveryID; unique delivery id number
	 * @return (Boolean) - return true on successful update, else false
	 */
	public Boolean verifyDelivery(int deliveryID) {
		try {
			if(connect()) {
				String insert = "Update deliveries set status = ? where deliveryID = ?";
		        PreparedStatement stmt = connection.prepareStatement(insert);
		        stmt.setString(1, "delivered");
				stmt.setInt(2, deliveryID);
				int row = stmt.executeUpdate();
	            System.out.println("Rows affected: " + row);//1
				connection.close();
				return true;
			}} catch (Exception ex) {
	        System.out.println("error - could not update database\n" + ex.getMessage());
		}
		return false;
	}
	
	/**
	 * Method updates status field for a delivery in db to delivered
	 * @param (int) - deliveryID; unique delivery id number
	 * @return (Boolean) - return true on successful update, else false
	 */
	public Boolean cancelDelivery(int deliveryID) {
		try {
			if(connect()) {
				String insert = "Update deliveries set status = ? where deliveryID = ?";
		        PreparedStatement stmt = connection.prepareStatement(insert);
		        stmt.setString(1, "canceled");
				stmt.setInt(2, deliveryID);
				int row = stmt.executeUpdate();
	            System.out.println("Rows affected: " + row);//1
				connection.close();
				return true;
			}} catch (Exception ex) {
	        System.out.println("error - could not update database\n" + ex.getMessage());
		}
		return false;
	}
}
