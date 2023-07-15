package com.abc.termproject.entity;
/**
 * 
 * @author tom
 *
 */

public class InvoiceItem {
	private int productID;
	private String name;
	private String description;
	private double price;
	private int quantity;
	
	public InvoiceItem(int productID, String name, String description, double price, int quantity) {
		this.productID = productID;
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setQuantity(quantity);
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
