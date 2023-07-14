package com.abc.termproject.entity;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
	private String firstName;
	private String lastName;
	private int userID;
	private List<InvoiceItem> itemList = new ArrayList<InvoiceItem>();

	public Invoice(String firstName, String lastName, int userID) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUserID(userID);
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public List<InvoiceItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<InvoiceItem> itemList) {
		this.itemList = itemList;
	}


}
