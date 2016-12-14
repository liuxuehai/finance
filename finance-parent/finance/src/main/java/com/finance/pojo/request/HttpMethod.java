package com.finance.pojo.request;

public enum HttpMethod {
	
	GET("get"), POST("post");
	private String val;

	HttpMethod(String val) {
		this.val = val;
	}

	public String val() {
		return this.val;
	}
}
