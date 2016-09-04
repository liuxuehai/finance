package com.pi.service.download;

import java.util.List;
import java.util.Map;

import org.apache.http.client.ResponseHandler;

public class DownloadRequest {

	String url;
	Map<String,String> param;
	ResponseHandler<List<String>> responseHandler;
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
	
	

}
