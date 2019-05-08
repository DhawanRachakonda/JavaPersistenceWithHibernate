package com.helloworld;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

@Embeddable
public class Address {

	@Column(name="street",nullable=false)
	protected String street;
	@Column(name="city",nullable=false)
	protected String city;
	@Column(name="pin_code",nullable=false)
	protected String pinCode;
	@ElementCollection
	@CollectionTable(name="contacts",joinColumns=@JoinColumn(name="user_id"))
	@Column(name="name",nullable=false)
	protected Set<String> contactList = new HashSet<String>();
	public Address() {
	}
	
	public Address(String street,String city, String pinCode,Set<String> contactList) {
		this.street = street;
		this.city = city;
		this.pinCode = pinCode;
		this.contactList = contactList;
	}

	public Set<String> getContactList() {
		return contactList;
	}

	public void setContactList(Set<String> contactList) {
		this.contactList = contactList;
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
