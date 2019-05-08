package com.helloworld;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

public class MoneyUserType implements CompositeUserType {

	/**
	 * This refers to java object property names which are mapped
	 */
	public String[] getPropertyNames() {
		return new String[] { "currCode", "amount" };
	}

	/**
	 * This refers to java object property types
	 */
	public Type[] getPropertyTypes() {
		return new Type[] { org.hibernate.type.StandardBasicTypes.CURRENCY,
				org.hibernate.type.StandardBasicTypes.DOUBLE };
	}

	/**
	 * This method fetches the property from the user type depending upon the
	 * index.It should follow getPropertyNames()s
	 */
	public Object getPropertyValue(Object component, int property) throws HibernateException {
		if (component == null)
			return null;
		else {
			if (property == 0)
				return ((Money) component).getCurrencyCode();
			else if (property == 1)
				return ((Money) component).getAmount();
		}

		return null;
	}

	/**
	 * This method sets the individual property in the custom user type
	 */
	public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
		if (value != null) {
			if (property == 0) {
				((Money) component).setCurrencyCode((Currency) value);
			} else if (property == 1)
				((Money) component).setAmount((Double) value);
		}
	}

	/**
	 * This method returns the custom user type class
	 */
	public Class returnedClass() {
		return Money.class;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}

	public int hashCode(Object x) throws HibernateException {
		if (x != null)
			return x.hashCode();
		else
			return 0;
	}

	/**
	 * This method constructs the custom user type from the resultset
	 */
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		String currCode = rs.getString(names[0]);
		Double balanceAmt = rs.getDouble(names[1]);

		if (currCode != null && balanceAmt != null) {
			return new Money(Currency.getInstance(currCode), balanceAmt);
		} else {
			return null;
		}
	}

	/**
	 * This method sets the value from the user type into prepared statement
	 */
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if (value != null) {
			st.setString(index, ((Money) value).getCurrencyCode().getCode());
			st.setDouble(index + 1, ((Money) value).getAmount());
		} else {
			st.setObject(index, null);
			st.setObject(index + 1, null);
		}
	}

	/**
	 * Deep copy
	 */
	public Object deepCopy(Object value) throws HibernateException {
		Money returnVal = new Money();
		Money currVal = (Money) value;
		Double amount = currVal.getAmount();
		Currency currCode = currVal.getCurrencyCode();

		returnVal.setCurrencyCode(Currency.getInstance(currCode.getCode()));
		returnVal.setAmount(new Double(amount.doubleValue()));

		return returnVal;
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object value, SharedSessionContractImplementor session) throws HibernateException {
		Object deepCopy = deepCopy(value);

		if (!(deepCopy instanceof Serializable))
			return (Serializable) deepCopy;
		else
			return null;

	}

	public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner) throws HibernateException {
		return deepCopy(cached);
	}

	public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		return deepCopy(original);
	}
	
}