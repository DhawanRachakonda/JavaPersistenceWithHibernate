package com.helloworld;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Parent;

@Embeddable
public class Image {

	@Column(nullable = false)
	protected String title;
	@Column(name = "file_name", nullable = false)
	protected String fileName;
	@Column(nullable = false)
	protected int width;
	@Column(nullable = false)
	protected int height;
	@Parent
	protected Item item;

	public Image(String title, String fileName, int width, int height) {
		this.title = title;
		this.fileName = fileName;
		this.width = width;
		this.height = height;
	}

	public Image() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		int result = this.title.hashCode();
		result = 31 * result + this.fileName.hashCode();
		result = 31 * result + this.width;
		result = 31 * result + this.height;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Image other = (Image) o;
		if (!this.title.equals(other.title))
			return false;
		if (!fileName.equals(other.fileName))
			return false;
		if (width != other.width)
			return false;
		if (height != other.height)
			return false;

		return true;
	}

	@Override
	public String toString() {
		return String.format("title : %s, File Name : %s, width %d, height : %d, Item Name : %s", this.title,
				this.fileName, this.width, this.height, this.item.ImageDescription);
	}
}
