package com.pi.base;

import java.util.Map;

public class BaseRequest extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;
	private Map<String, String> param;
	private Map<String, String> header;
	private HttpMethod httpMethod;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getParam() {
		return param;
	}

	public void setParam(Map<String, String> param) {
		this.param = param;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}
}
