package com.helloworld;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
	public Set<CategoryItem> getCategoryItems() {
		return categoryItems;
	}
	public void setCategoryItems(Set<CategoryItem> categoryItems) {
		this.categoryItems = categoryItems;
	}
	@ElementCollection
	@CollectionTable(name="category_item",joinColumns=@JoinColumn(name="category_id"))
	protected Set<CategoryItem> categoryItems = new HashSet<>();
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
}
