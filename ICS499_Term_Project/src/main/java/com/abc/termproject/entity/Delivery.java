package com.abc.termproject.entity;

public class Delivery {
	private int deliveryID;
	private int driverID;
	private String invoiceDate;
	private int customerID;
	private String status;
	
	public Delivery(int deliveryID, int driverID, String invoiceDate, int customerID, String status) {
		this.setDeliveryID(deliveryID);
		this.setDriverID(driverID);
		this.setInvoiceDate(invoiceDate);
		this.setCustomerID(customerID);
		this.setStatus(status);
	}

	public int getDeliveryID() {
		return deliveryID;
	}

	public void setDeliveryID(int deliveryID) {
		this.deliveryID = deliveryID;
	}

	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
