package com.helloworld;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(name="street")
	protected String street;
	@Column(name="city")
	protected String city;
	@Column(name="pin_code")
	protected String pinCode;
	public Address() {
	}
	
	public Address(String street,String city, String pinCode) {
		this.street = street;
		this.city = city;
		this.pinCode = pinCode;
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

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
}
