package com.helloworld;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;

	@Column(name="category_name")
	protected String categoryName;
	
	@OneToMany(mappedBy="category")
	protected Set<CategoryItem> categorizedItems = new HashSet<>();
	
	public Category(String name) {
		this.categoryName = name;
	}
	
	public Category(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<CategoryItem> getCategorizedItems() {
		return categorizedItems;
	}

	public void setCategorizedItems(Set<CategoryItem> categorizedItems) {
		this.categorizedItems = categorizedItems;
	}
}
