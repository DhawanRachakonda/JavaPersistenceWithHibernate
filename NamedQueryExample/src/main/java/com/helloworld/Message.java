package com.helloworld;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;

@Entity
@Table(name="message")
@NamedQueries(
		@NamedQuery(name="listMessages",query="select m.id,m.text from Message m")
		)
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String text;
	public Message() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Message(String text) {
		this.text = text;
	}
	
	public Message(long id, String text) {
		this.id = id;
		this.text = text;
	}
	
}
