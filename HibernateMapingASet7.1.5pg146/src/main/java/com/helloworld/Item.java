package com.helloworld;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@Column(name="image_desc")
	protected String ImageDescription;
	@GenericGenerator(name="item_id_generator",strategy="sequence")
	@ElementCollection
	@CollectionTable(name="images")
	@Column(name="file_path")
	@CollectionId(columns=@Column(name="mage_id"),type=@Type(type="long"), generator = "item_id_generator")
	protected Collection<String> imageList = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImageDescription() {
		return ImageDescription;
	}
	public void setImageDescription(String imageDescription) {
		ImageDescription = imageDescription;
	}
	public Collection<String> getImageList() {
		return imageList;
	}
	public void setImageList(Collection<String> imageList) {
		this.imageList = imageList;
	}
	
}
