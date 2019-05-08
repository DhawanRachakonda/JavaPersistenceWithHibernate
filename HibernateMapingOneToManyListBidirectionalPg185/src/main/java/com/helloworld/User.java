package com.helloworld;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@OneToMany(mappedBy="buyer",cascade=CascadeType.PERSIST)
	protected Set<Item> boughtItems = new HashSet<Item>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Item> getBoughtItems() {
		return boughtItems;
	}
	public void setBoughtItems(Set<Item> boughtItems) {
		this.boughtItems = boughtItems;
	}
	
	public User(){}
	
	public User(Set<Item> item) {
		this.boughtItems = item;
	}
}
