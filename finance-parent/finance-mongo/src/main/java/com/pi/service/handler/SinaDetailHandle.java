package com.pi.service.handler;

import java.util.List;

public class SinaDetailHandle  extends AbstractResponseHandler{

	@Override
	public void filter(List<String> resultList) {
		resultList.remove(0);
	}
}
