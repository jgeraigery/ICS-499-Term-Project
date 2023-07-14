package com.abc.termproject.entity;

public class InvoiceItem {
	private int productID;
	private String name;
	private String description;
	private double price;
	
	public InvoiceItem(int productID, String name, String description, double price) {
		this.productID = productID;
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
}
