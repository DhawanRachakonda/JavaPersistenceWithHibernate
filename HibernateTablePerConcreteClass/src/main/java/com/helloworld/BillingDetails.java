package com.helloworld;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class BillingDetails {

	@NotNull
	protected String owner;
}
