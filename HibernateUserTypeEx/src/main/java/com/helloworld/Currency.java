package com.helloworld;

public class Currency {

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Currency(String currCode) {
		this.code = currCode;
	}
	
	public Currency() {}

	public static Currency getInstance(String code2) {
		return new Currency(code2);
	}
}
