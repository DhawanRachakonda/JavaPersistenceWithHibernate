package com.helloworld;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@Column(name="image_desc")
	protected String ImageDescription;
	@ElementCollection
	@CollectionTable(name="images")
	protected Map<FileName,Image> imageList = new HashMap<>();
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
	
	public Map<FileName, Image> getImageList() {
		return imageList;
	}
	public void setImageList(Map<FileName, Image> imageList) {
		this.imageList = imageList;
	}
	@Override
	public String toString() {
		String str = String.format("Image desc %s", this.ImageDescription)+"\n";
		for(FileName imageKey : this.imageList.keySet()) {
			str += this.imageList.get(imageKey)+"\n";
		}
		return str;
	}
}
