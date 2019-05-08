package com.helloworld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@Column
	protected String name;
	@OneToMany(mappedBy="item",cascade={CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval=true)
	protected Collection<Bid> bids = new ArrayList<>();
	
	public Collection<Bid> getBids() {
		return bids;
	}

	public void setBids(Collection<Bid> bids) {
		this.bids = bids;
	}

	public Item() {}
	
	public Item(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
