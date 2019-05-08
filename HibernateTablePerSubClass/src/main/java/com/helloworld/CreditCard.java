package com.helloworld;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="credit_card")
@PrimaryKeyJoinColumn(name="creditcard_id")
public class CreditCard extends BillingDetails {

	@Column(name="card_number")
	@NotNull
	protected String cardNumber;
	@Column(name="exp_month")
	@NotNull
	protected String expMonth;
	@Column(name="exp_year")
	@NotNull
	protected String expYear;
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}
	public String getExpYear() {
		return expYear;
	}
	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}
	
	public CreditCard() {}
	
	public CreditCard(String owner,String cardNumber, String expMonth, String expYear) {
		this.owner = owner;
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}
}
