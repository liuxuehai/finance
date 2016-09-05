package com.pi.service.download;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.http.Header;
import org.apache.http.client.ResponseHandler;

public class DownloadRequest {

	String url;
	Map<String, String> param;
	ResponseHandler<List<String>> responseHandler;
	List<Header> headers;

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

	public ResponseHandler<List<String>> getResponseHandler() {
		return responseHandler;
	}

	public void setResponseHandler(ResponseHandler<List<String>> responseHandler) {
		this.responseHandler = responseHandler;
	}

	public List<Header> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
