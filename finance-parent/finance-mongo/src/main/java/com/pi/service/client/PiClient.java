package com.pi.service.client;

import java.util.Map;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.pi.base.BaseRequest;
import com.pi.base.HttpMethod;

@Component
public class PiClient {

	private Logger logger = LoggerFactory.getLogger(PiClient.class);

	public HttpRequestBase getRequestBase(BaseRequest<? extends Object> request) {
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
