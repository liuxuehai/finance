package com.pi.service.download;

import java.util.Map;

public class DownloadRequest {

	String url;
	Map<String,String> param;
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

}
