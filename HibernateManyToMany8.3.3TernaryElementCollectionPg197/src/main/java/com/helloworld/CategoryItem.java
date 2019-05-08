package com.helloworld;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class CategoryItem {
	
	@Column(name="added_on")
	@NotNull
	protected Date addedOn = new Date();
	@ManyToOne
	@JoinColumn(name="item_id",updatable=false)
	protected Item item;
	@ManyToOne
	@JoinColumn(name="user_id",updatable=false)
	protected User user;
	
	public CategoryItem() {}
	
	public CategoryItem(Item item,User user) {
		this.item = item;
		this.user = user;
	}
	
	public Date getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
