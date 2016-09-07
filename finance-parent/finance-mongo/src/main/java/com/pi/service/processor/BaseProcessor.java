package com.pi.service.processor;

import com.pi.base.BaseRequest;

public interface BaseProcessor<T extends Object> {

	void process(BaseRequest<T> request, T object);
}
