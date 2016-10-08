package com.pi.service.sh;


import com.pi.base.ExcelRequest;
import com.pi.stock.base.BaseObject;

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
