package com.helloworld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bid {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bid_id")
	protected Long id;
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false)
	protected Item item;
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	protected User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="amount")
	protected double amount;
	
	public Bid() {}
	
	public Bid(Item item, double amount,User user) {
		this.item = item;
		this.amount = amount;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
