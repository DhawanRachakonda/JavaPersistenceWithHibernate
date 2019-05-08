package com.helloworld;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="state_enum")
@DynamicInsert
@DynamicUpdate
public class State {

	@Column(name="name")
	@ColumnTransformer(read="substr(name,1,2)",write="concat(? , ' (IND) ')")
	private String name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	//@Temporal(TemporalType.TIMESTAMP)
	@Generated(GenerationTime.INSERT)
	@Column(name="create_date", insertable=false, updatable=false)
	private LocalDateTime createdAt;
	
	//@Temporal(TemporalType.TIMESTAMP)
	//@Generated(GenerationTime.INSERT)
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	//@Generated(GenerationTime.INSERT)
	@Column(name="dup_id")
	private long dupId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="text_type")
	private TextType textType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public long getDupId() {
		return dupId;
	}
	public void setDupId(long dupId) {
		this.dupId = dupId;
	}
	public TextType getTextType() {
		return textType;
	}
	public void setTextType(TextType textType) {
		this.textType = textType;
	}
	
}
