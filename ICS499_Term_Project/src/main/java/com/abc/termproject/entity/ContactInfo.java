package com.abc.termproject.entity;

public class ContactInfo {
	private int sNumber;
	private String street;
	private String city;
	private String state;
	private int zip;
	private int prefix;
	private int pNumber;
	
	public ContactInfo(int sNumber, String street, String city, String state, int zip, int prefix, int pNumber) {
		this.setsNumber(sNumber);
		this.setStreet(street);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setPrefix(prefix);
		this.setpNumber(pNumber);
	}

	public int getsNumber() {
		return sNumber;
	}

	public void setsNumber(int sNumber) {
		this.sNumber = sNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public int getPrefix() {
		return prefix;
	}

	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}

	public int getpNumber() {
		return pNumber;
	}

	public void setpNumber(int pNumber) {
		this.pNumber = pNumber;
	}
	
}
