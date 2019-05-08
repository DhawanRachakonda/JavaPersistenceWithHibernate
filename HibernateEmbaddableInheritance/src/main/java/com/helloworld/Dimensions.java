package com.helloworld;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@AttributeOverrides( {@AttributeOverride(name="name",column=@Column(name="dimension_name")),
		@AttributeOverride(name="symbol",column=@Column(name="dimension_symbol"))} )
public class Dimensions extends Measurement{

	@NotNull
	protected BigDecimal depth;
	@NotNull
	protected BigDecimal height;
	@NotNull
	protected BigDecimal width;
	public BigDecimal getDepth() {
		return depth;
	}
	public void setDepth(BigDecimal depth) {
		this.depth = depth;
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	public BigDecimal getWidth() {
		return width;
	}
	public void setWidth(BigDecimal symbol) {
		this.width = symbol;
	}
	
	public Dimensions() {}
	
	public Dimensions(BigDecimal depth,BigDecimal height,BigDecimal width,String name,String symbol) {
		this.depth = depth;
		this.height = height;
		this.width = width;
		this.name = name;
		this.symbol = symbol;
	}
}
