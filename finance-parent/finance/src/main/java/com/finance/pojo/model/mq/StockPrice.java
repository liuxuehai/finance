package com.finance.pojo.model.mq;

import com.finance.pojo.model.base.BaseObject;

public class StockPrice extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String startDate;
	private String endDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
