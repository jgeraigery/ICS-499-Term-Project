package com.abc.termproject.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ReadUtilityCSV {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/EZDB";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "root";

	public void readCSV() {
		String filePath = "C:\\Users\\salay\\Desktop\\Summer 2023\\499\\Team Homestretch 06-16-23\\ICS-499-Term-Project\\Sample CSV file.csv";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	             FileReader fileReader = new FileReader(filePath);
	             BufferedReader reader = new BufferedReader(fileReader)) {

	            String line;
	            while ((line = reader.readLine()) != null) {
	                // Process each line of the CSV file
	                String[] data = line.split(",");
	                // Perform further processing with the data
	                retrieveDataFromDatabase(connection, data);
	            }
	        } catch (IOException | SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private void retrieveDataFromDatabase(Connection connection, String[] data) throws SQLException {
	   // SELECT fields, FROM table, WHERE condition EX:(based on invoiceID)
	    	String sql = "SELECT itemID, invoiceID, userID, invoiceDate, productID, quantity FROM invoiceItem WHERE invoiceID = ?";

	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, data[0]); // Set the value for the condition column

	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                // Retrieve and process each row of data
	                String column1Value = resultSet.getString("itemID");
	                String column2Value = resultSet.getString("invoiceID");
	                String column3Value = resultSet.getString("userID");
	                String column4Value = resultSet.getString("invoiceDate");
	                String column5Value = resultSet.getString("productID");
	                String column6Value = resultSet.getString("quantity");

	                // Perform further processing with the retrieved data
	                System.out.println(column1Value + ", " + column2Value + ", " + column3Value + ", " 
	                		+ column4Value + ", " + column5Value + ", " + column6Value);
	            }
	        }
	    }
	}
