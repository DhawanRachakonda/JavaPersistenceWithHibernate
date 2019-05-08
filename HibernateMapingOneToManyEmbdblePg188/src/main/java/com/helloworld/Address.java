package com.helloworld;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

	@NotNull
	protected String street;
	@NotNull
	@Column(name="zip_code")
	protected String zipCode;
	@NotNull
	@Column(name="city")
	protected String city;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="delivery_user_id",nullable=false)
	protected Set<Shipment> deliveries = new HashSet<>();
	
	public Address() {}
	
	public Address(String street, String zipCode, String city) {
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public Set<Shipment> getDeliveries() {
		return deliveries;
	}
	public void setDeliveries(Set<Shipment> deliveries) {
		this.deliveries = deliveries;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
