package com.helloworld;

import javax.persistence.DiscriminatorColumn;
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
public abstract class BillingDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	
	@NotNull
	protected String owner;
}
