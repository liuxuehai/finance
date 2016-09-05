package com.pi.service.sh;


import com.pi.base.BaseObject;
import com.pi.base.ExcelRequest;

public class StockInfoEvent  extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExcelRequest request;

	public ExcelRequest getRequest() {
		return request;
	}

	public void setRequest(ExcelRequest request) {
		this.request = request;
	}
}
