package com.finance.service.processor;

import com.finance.pojo.request.BaseRequest;

public interface BaseProcessor<T extends Object> {

	void process(BaseRequest<T> request, T object);
}
