package com.helloworld;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "message")
@NamedQueries(@NamedQuery(name = "listMessages", query = "select m.id,m.text from Message m"))
@GenericGenerator(name = "MESSAGE_GENERATOR", strategy = "enhanced-sequence", parameters = {
		@Parameter(name = "sequence_name", value = "Message_Sequence"),
		@Parameter(name = "initial_value", value = "400") })
@DynamicInsert
@DynamicUpdate
@Immutable
public final class Message {
	@Id
	@GeneratedValue(generator = "MESSAGE_GENERATOR")
	private final Long id;
	@Size(min = 5, max = 120, message = "Text Too Short")
	@Column
	private final String text;

	@Formula("substr(text , 1,3)")
	@Access(AccessType.FIELD)
	protected String firstThreetextChars;
	
	

	public Message() {
		this.id = 0L;
		this.text = "";
		this.firstThreetextChars = "";
	}

	public Long getId() {
		return id;
	}

	public String getFirstThreetextChars() {
		return firstThreetextChars;
	}

	public void setFirstThreetextChars(String firstThreetextChars) {
		this.firstThreetextChars = firstThreetextChars;
	}
	
	/*
	 * public void setId(Long id) { this.id = id; }
	 */
	public String getText() {
		return text;
	}
	/*
	 * public void setText(String text) { this.text = text; }
	 */

	public Message(String text) {
		System.out.println("First1 -> ");
		this.text = text;
		this.id = 0L;
	}

	public Message(long id, String text) {
		this.id = id;
		this.text = text;
	}

}