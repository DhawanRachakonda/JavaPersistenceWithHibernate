package com.helloworld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bid {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected Long id;
	@Column(name="amount")
	protected double amount;
	@ManyToOne(optional=false)
	protected Item item;
	
	public Bid() {}
	
	public Bid(double amount) {
		this.amount = amount;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null) return false;
		if(!(other instanceof Bid)) return false;
		Bid that = (Bid) other;
		if(this.getAmount() != that.getAmount()) return false;
		if(this.getItem().getId() != that.getItem().getId()) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = (int)getAmount();
		result = 31 * result + getItem().getId().hashCode();
		return result;
	}
	
}