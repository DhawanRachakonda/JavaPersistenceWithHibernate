package com.helloworld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	protected Long id;
	@Column(name="user_name")
	@NotNull
	protected String userName;
	@OneToOne(fetch=FetchType.LAZY,optional=false)
	@PrimaryKeyJoinColumn
	protected Address shippingAddress;
	
	protected User() {}
	
	public User(Long id, String userName) {
		this.id = id;
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
