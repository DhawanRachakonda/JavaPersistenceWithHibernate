package com.helloworld;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("CC")
@SecondaryTable(name="credit_card",pkJoinColumns = @PrimaryKeyJoinColumn(name="creditcard_id"))
public class CreditCard extends BillingDetails {

	@Column(table="credit_card",name="card_number",nullable=false)
	@NotNull
	protected String cardNumber;
	@Column(table="credit_card",name="exp_month",nullable=false)
	@NotNull
	protected String expMonth;
	@Column(table="credit_card",name="exp_year",nullable=false)
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
	
	public CreditCard() {super();}
	
	public CreditCard(String owner,String account, String bankName, String swift,String cardNumber, String expMonth, String expYear) {
		super(owner, account, bankName, swift);
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}
}
