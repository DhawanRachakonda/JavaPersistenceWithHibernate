package com.helloworld;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="bd_type")
@Table(name="billing_details")
@DiscriminatorValue("BA")
public class BillingDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	@NotNull
	protected String owner;
	@NotNull
	@Column(name="account")
	protected String account;
	@Column(name="bank_name")
	@NotNull
	protected String bankName;
	@Column(name="swift")
	@NotNull
	protected String swift;
	
	public BillingDetails(String owner,String account, String bankName, String swift) {
		this.owner = owner;
		this.account = account;
		this.bankName = bankName;
		this.swift = swift;
	}
	
	public BillingDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
}