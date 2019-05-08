package com.helloworld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Address {

	@Id
	@GenericGenerator(name="addressGenerator",strategy="foreign",parameters=@Parameter(name="property",value="user"))
	@GeneratedValue(generator="addressGenerator")
	protected Long id;
	@NotNull
	protected String street;
	@NotNull
	@Column(name="zip_code")
	protected String zipCode;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@NotNull
	protected String city;
	@OneToOne(optional=false)
	@PrimaryKeyJoinColumn
	protected User user;
	
	public Address(User user) {
		this.user = user;
	}
	
	public Address(User user,String street,String zipCode,String city) {
		this.user=user;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setCity(String city) {
		this.city = city;
	}
}
