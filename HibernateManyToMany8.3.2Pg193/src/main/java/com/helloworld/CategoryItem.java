package com.helloworld;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name="category_item")
@Immutable
@DynamicInsert
@DynamicUpdate
public class CategoryItem {

	public static class Id implements Serializable {
		@Column(name="category_id")
		protected Long categoryId;
		@Column(name="item_id")
		protected Long itemId;
		
		public Id() {}
		
		public Id(long categoryId, Long itemId) {
			this.categoryId = categoryId;
			this.itemId = itemId;
		}
		
		public boolean equals(Object o) {
			if(o!=null && o instanceof Id) {
				Id that = (Id) o;
				return this.categoryId.equals(that.categoryId) && this.itemId.equals(that.itemId);
			}
			return false;
		}
		
		public int hashCode() {
			return categoryId.hashCode() + itemId.hashCode();
		}
	}
	
	@EmbeddedId
	protected Id id = new Id();
	
	@Column(name="added_by",updatable = false)
	@NotNull
	protected String addedBy;
	
	@Column(name="added_on",updatable=false)
	@NotNull
	protected Date addOn = new Date();
	
	@ManyToOne
	@JoinColumn(name="category_id",insertable=false,updatable=false)
	protected Category category;
	
	@ManyToOne
	@JoinColumn(name="item_id",insertable=false,updatable=false)
	protected Item item;
	
	public CategoryItem(String addedByUserName,Category category,Item item) {
		this.addedBy = addedByUserName;
		this.category = category;
		this.item = item;
		
		this.id.categoryId = category.getId();
		this.id.itemId = item.getId();
		
		category.getCategorizedItems().add(this);
		item.getCategorizedItems().add(this);
	}
}
