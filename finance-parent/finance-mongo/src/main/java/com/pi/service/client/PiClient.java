package com.pi.service.client;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.pi.base.BaseRequest;
import com.pi.base.HttpMethod;

@Component
public class PiClient {

	private Logger logger = LoggerFactory.getLogger(PiClient.class);

	public HttpRequestBase getRequestBase(BaseRequest request) {
		HttpRequestBase requestBase = null;
		if (request.getHttpMethod() == HttpMethod.GET) {
			requestBase = new HttpGet(request.getUrl());
		} else if (request.getHttpMethod() == HttpMethod.POST) {
			requestBase = new HttpPost(request.getUrl());
		}
		// add request header
		if (!CollectionUtils.isEmpty(request.getHeader())) {
			for (Map.Entry<String, String> entry : request.getHeader().entrySet()) {
				requestBase.setHeader(entry.getKey(), entry.getValue());
			}
		}
		return requestBase;
	}
}
