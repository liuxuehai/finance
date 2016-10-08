package com.pi.stock.mq;

import com.pi.stock.base.BaseObject;

public class Message extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type;
	private Object data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
