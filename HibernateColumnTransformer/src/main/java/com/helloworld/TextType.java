package com.helloworld;

public enum TextType {
HIGH_LEVEL("HL"),MEDIUM_LEVEL("ML"),LOW_LEVEL("LL");
	private String type;
	TextType(String type) {
		this.type = type;
	}
	public String getText() {
		return this.type;
	}
}
