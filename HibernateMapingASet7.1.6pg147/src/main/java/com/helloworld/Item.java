package com.helloworld;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderColumn;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@Column(name="image_desc")
	protected String ImageDescription;
	@ElementCollection
	@CollectionTable(name="images")
	@OrderColumn(name="ordr_id")
	@Column(name="file_path")
	protected List<String> imageList = new ArrayList<>();
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
	public List<String> getImageList() {
		return imageList;
	}
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	
}
