package com.helloworld;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	protected Address homeAddress;
	@AttributeOverrides({
			@AttributeOverride(name="street",column= @Column(name="office_street")),
			@AttributeOverride(name="city", column= @Column(name="office_city")),
			@AttributeOverride(name="pinCode", column= @Column(name="office_pin_code"))
	})
	protected Address officeAddress;
	public User() {
		
	}
	
	public User(Address address) {
		this.homeAddress = address;
		this.officeAddress = address;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	
}
