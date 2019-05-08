package com.helloworld;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@AttributeOverride(name="owner" , column= @Column(name="CC_OWNER", nullable=false))
@Table(name="credit_card")
public class CreditCard extends BillingDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@Column(name="card_number")
	@NotNull
	protected String cardNumber;
	@Column(name="exp_month")
	@NotNull
	protected String expMonth;
	@Column(name="exp_year")
	@NotNull
	protected String expYear;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
