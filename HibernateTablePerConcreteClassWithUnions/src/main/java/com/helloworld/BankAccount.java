package com.helloworld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="bank_account")
public class BankAccount extends BillingDetails{

	@NotNull
	@Column(name="account")
	protected String account;
	@Column(name="bank_name")
	@NotNull
	protected String bankName;
	@Column(name="swift")
	@NotNull
	protected String swift;
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
	
	public BankAccount() {}
	
	public BankAccount(String owner,String account, String bankName, String swift) {
		this.owner = owner;
		this.account = account;
		this.bankName = bankName;
		this.swift = swift;
	}
}
