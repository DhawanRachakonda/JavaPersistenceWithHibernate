package com.helloworld;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
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
	@AttributeOverride(name="fileName", column=@Column(name="f_name", nullable=false))
	protected Set<Image> imageList = new HashSet<>();
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
	
	public Set<Image> getImageList() {
		return imageList;
	}
	public void setImageList(Set<Image> imageList) {
		this.imageList = imageList;
	}
	@Override
	public String toString() {
		String str = String.format("Image desc %s", this.ImageDescription)+"\n";
		for(Image image : this.imageList) {
			str += image.toString()+"\n";
		}
		return str;
	}
}
