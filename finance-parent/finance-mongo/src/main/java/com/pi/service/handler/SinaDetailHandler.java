package com.pi.service.handler;

import java.util.List;

public class SinaDetailHandler  extends AbstractResponseHandler{

	@Override
	public void filter(List<String> resultList) {
		resultList.remove(0);
	}
}
