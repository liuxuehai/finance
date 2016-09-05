package com.pi.base;

import java.util.List;

import org.apache.http.client.ResponseHandler;

public class ExcelRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ResponseHandler<List<String>> responseHandler;

	public ResponseHandler<List<String>> getResponseHandler() {
		return responseHandler;
	}

	public void setResponseHandler(ResponseHandler<List<String>> responseHandler) {
		this.responseHandler = responseHandler;
	}

}
