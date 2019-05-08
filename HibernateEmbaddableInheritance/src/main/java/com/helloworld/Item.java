package com.helloworld;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	protected Dimensions dimension;
	protected Weight weight;
	public Item() {}
	
	public Item(Dimensions dimension, Weight weight) {
		this.dimension = dimension;
		this.weight = weight;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dimensions getDimension() {
		return dimension;
	}

	public void setDimension(Dimensions dimension) {
		this.dimension = dimension;
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}
}
