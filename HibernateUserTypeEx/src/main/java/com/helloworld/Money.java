package com.helloworld;

public class Money {

	private Currency currencyCode;
	private Double amount;
	public Currency getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(Currency currencyCode) {
		this.currencyCode = currencyCode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Money() {}
	
	public Money(Currency currency, Double amount) {
		this.currencyCode = currency;
		this.amount = amount;
	}
}
