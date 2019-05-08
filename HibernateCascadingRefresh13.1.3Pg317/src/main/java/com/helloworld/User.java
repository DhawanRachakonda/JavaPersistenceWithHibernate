package com.helloworld;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="bill_id",nullable=false)
	protected List<BillingDetails> bills = new ArrayList<BillingDetails>();
	public User() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<BillingDetails> getBills() {
		return bills;
	}
	public void setBills(List<BillingDetails> bills) {
		this.bills = bills;
	}
	
}
