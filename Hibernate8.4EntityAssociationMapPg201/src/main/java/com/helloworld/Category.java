package com.helloworld;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	protected Long categoryId;
	@Column(name="category_name")
	protected String categoryName;
	@ManyToMany(cascade=CascadeType.PERSIST)
	@MapKeyJoinColumn(name="item_id")
	@JoinTable(name="category_item",joinColumns=@JoinColumn(name="category_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
	protected Map<Item,User> itemAddedBy = new HashMap<>();
	public Category() {}
	public Category(String name) {
		this.categoryName = name;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Map<Item, User> getItemAddedBy() {
		return itemAddedBy;
	}
	public void setItemAddedBy(Map<Item, User> itemAddedBy) {
		this.itemAddedBy = itemAddedBy;
	}
}
