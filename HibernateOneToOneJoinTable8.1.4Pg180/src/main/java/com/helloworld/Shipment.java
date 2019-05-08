package com.helloworld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Shipment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="shipment_id")
	protected Long id;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinTable(name="shipment_item",joinColumns=@JoinColumn(name="shipment_id",nullable=false,unique=true),inverseJoinColumns=@JoinColumn(name="item_id",nullable=false,unique=true))
	protected Item item;
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
	
}
