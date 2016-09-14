package com.pi.base;

import java.util.Map;

import org.apache.http.client.ResponseHandler;

import com.pi.service.processor.BaseProcessor;
import com.pi.stock.model.BaseObject;

public class BaseRequest<T extends Object> extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;
	private Map<String, String> param;
	private Map<String, String> header;
	private HttpMethod httpMethod;
	private ResponseHandler<T> responseHandler;
	private BaseProcessor<T> processor;

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

	public ResponseHandler<? extends Object> getResponseHandler() {
		return responseHandler;
	}

	public void setResponseHandler(ResponseHandler<T> responseHandler) {
		this.responseHandler = responseHandler;
	}

	public BaseProcessor<T> getProcessor() {
		return processor;
	}

	public void setProcessor(BaseProcessor<T> processor) {
		this.processor = processor;
	}
}
