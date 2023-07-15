package com.abc.termproject.entity;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
	private String name;
	private int userID;
	private List<InvoiceItem> itemList = new ArrayList<InvoiceItem>();

	public Invoice(String name, int userID) {
		this.setName(name);
		this.setUserID(userID);
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
	
	public double getTotal() {
		double total = 0;
		for(InvoiceItem i : itemList) {
			total += (i.getPrice()*i.getQuantity());
		}
		return total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
