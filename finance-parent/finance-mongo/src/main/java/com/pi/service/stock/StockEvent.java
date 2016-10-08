package com.pi.service.stock;

import com.pi.base.StockRequest;
import com.pi.stock.base.BaseObject;

public class StockEvent extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StockRequest request;

	public StockRequest getRequest() {
		return request;
	}

	public void setRequest(StockRequest request) {
		this.request = request;
	}

}
