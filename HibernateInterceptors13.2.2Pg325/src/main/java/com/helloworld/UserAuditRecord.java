package com.helloworld;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserAuditRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@Column(nullable=false)
	protected String message;
	@Column(name="entity_class")
	protected String entityClass;
	@Column(name="user_id",nullable=false)
	protected Long userId;
	@Column(name="created_on")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdOn;
	
	public UserAuditRecord() {}
	
	public UserAuditRecord(String message, String className,Long userId) {
		this.message = message;
		this.entityClass = className;
		this.userId = userId;
		this.createdOn = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}
