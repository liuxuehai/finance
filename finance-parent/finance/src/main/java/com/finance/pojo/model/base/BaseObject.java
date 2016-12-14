package com.finance.pojo.model.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
