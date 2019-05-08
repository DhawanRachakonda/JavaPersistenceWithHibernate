package com.helloworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.SortNatural;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@Column(name="image_desc")
	protected String ImageDescription;
	@ElementCollection
	@CollectionTable(name="images")
	@MapKeyColumn(name="file_identifier")
	@Column(name="file_path")
	@OrderBy(clause = "file_identifier desc")
	protected Map<String, String> imageList = new HashMap<>();
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
	public Map<String, String> getImageList() {
		return imageList;
	}
	public void setImageList(Map<String, String> imageList) {
		this.imageList = imageList;
	}
	
	@Override
	public String toString() {
		String str = String.format("Image desc %s", this.ImageDescription)+"\n";
		for(String imageKey : this.imageList.keySet()) {
			str += this.imageList.get(imageKey)+"\n";
		}
		return str;
	}
}
