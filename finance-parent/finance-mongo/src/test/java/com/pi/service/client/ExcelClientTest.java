package com.pi.service.client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pi.base.ExcelRequest;
import com.pi.base.HttpMethod;
import com.pi.common.Constants;
import com.pi.service.handler.StockInfoHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class ExcelClientTest {

	@Autowired
	ExcelClient excelClient;

	@Test
	public void testGet() {
		String url = "http://query.sse.com.cn/security/stock/downloadStockListFile.do?stockType=1";
		ExcelRequest request = new ExcelRequest();
		request.setUrl(url);
		Map<String, String> param = new HashMap<String, String>();
		StockInfoHandler sinaDetailHandle = new StockInfoHandler();
		Map<String, String> header = new HashMap<String, String>();
		header.put("User-Agent", Constants.USER_AGENT);
		header.put("Accept-Encoding", Constants.Accept_Encoding);
		header.put("Referer", Constants.Referer_SHANGHAI);
		header.put("Accept-Language", Constants.Accept_Language);
		header.put("Content-Type", Constants.Content_Excel);
		header.put("Accept", Constants.Accept);
		request.setHeader(header);

		request.setResponseHandler(sinaDetailHandle);
		request.setParam(param);
		request.setHttpMethod(HttpMethod.GET);
		excelClient.get(request);
	}
}
