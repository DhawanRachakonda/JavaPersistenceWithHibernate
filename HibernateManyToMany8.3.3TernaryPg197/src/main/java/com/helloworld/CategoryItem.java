package com.helloworld;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
public class CategoryItem {

	@Embeddable
	protected class Id implements Serializable{
		@Column(name="category_id")
		@NotNull
		protected Long categoryId;
		@Column(name="item_id")
		@NotNull
		protected Long itemId;
		@Column(name="user_id")
		@NotNull
		protected Long userId;
		
		/*public Id(long categoryId, Long itemId, Long userId) {
			this.categoryId = categoryId;
			this.itemId = itemId;
			this.userId = userId;
		}*/
		
		public Long getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
		public Long getItemId() {
			return itemId;
		}
		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
	}
	
	@EmbeddedId
	protected Id id = new Id();
	
	@Column(name="added_on",nullable=false)
	protected Date addedOn = new Date();
	
	@ManyToOne
	@JoinColumn(name="category_id",insertable=false,updatable=false)
	protected Category category;
	@ManyToOne
	@JoinColumn(name="item_id",insertable=false,updatable=false)
	protected Item item;
	@ManyToOne
	@JoinColumn(name="user_id",insertable=false,updatable=false)
	protected User user;
	
	public CategoryItem() {}
	
	public CategoryItem(Category category,Item item,User user) {
		this.id.categoryId = category.getCategoryId();
		this.id.itemId = item.getItemId();
		this.id.userId = user.getUserId();
		
		this.category = category;
		this.item = item;
		this.user = user;
	}
	
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public Date getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
