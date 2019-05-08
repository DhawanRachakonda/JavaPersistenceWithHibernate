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

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@Column(name="image_desc")
	protected String ImageDescription;
	@ElementCollection
	@CollectionTable(name="images",joinColumns=@JoinColumn(name="image_id"))
	@Column(name="file_path")
	protected Set<String> imageList = new HashSet<>();
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
	public Set<String> getImageList() {
		return imageList;
	}
	public void setImageList(Set<String> imageList) {
		this.imageList = imageList;
	}
	
}
