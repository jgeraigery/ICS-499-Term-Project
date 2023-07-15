package com.abc.termproject.entity;

public class DateInvoiceNumber {
	private int userID;
	private int invoiceID;
	private String date;
	
	public DateInvoiceNumber(int userID, int invoiceID, String date) {
		this.setUserID(userID);
		this.setInvoiceID(invoiceID);
		this.setDate(date);
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
