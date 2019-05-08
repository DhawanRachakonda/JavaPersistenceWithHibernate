package com.helloworld;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Check;

@Entity
//, (not substring(lower(user_name),0,5) = 'admin')
@Table(uniqueConstraints=@UniqueConstraint(name="UNQ_USERNAME_EMAIL",columnNames={"user_name","email"}))
//@Check(constraints="auction_start < auction_end AND user_name <=> 'admin'")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@Column(name="user_name",nullable=false)
	protected String userName;
	@Column(name="email",nullable=false)
	protected String email;
	@Column(name="auction_start",nullable=false)
	protected Date auctionStart;
	@Column(name="auction_end",nullable=false)
	protected Date auctionEnd;
	
	public User() {}
			
	public User(String userName, String email,Date actionStart,Date auctionEnd) {
		this.userName = userName;
		this.email = email;
		this.auctionStart = actionStart;
		this.auctionEnd = auctionEnd;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getAuctionStart() {
		return auctionStart;
	}

	public void setAuctionStart(Date auctionStart) {
		this.auctionStart = auctionStart;
	}

	public Date getAuctionEnd() {
		return auctionEnd;
	}

	public void setAuctionEnd(Date auctionEnd) {
		this.auctionEnd = auctionEnd;
	}
}
