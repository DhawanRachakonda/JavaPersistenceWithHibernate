package com.helloworld;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FileName {

	@Column(nullable=false)
	protected String name;
	@Column(nullable=false)
	protected String extension;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public FileName(){}
	
	public FileName(String name, String extension) {
		this.name = name;
		this.extension = extension;
	}
	
	@Override
	public int hashCode() {
		int result = this.name.hashCode();
		result = 31 * result + this.extension.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FileName other = (FileName) o;
		if (!this.name.equals(other.name))
			return false;
		if (!extension.equals(other.extension))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("File Name : %s, extension : %s", this.name,this.extension);
	}
}
