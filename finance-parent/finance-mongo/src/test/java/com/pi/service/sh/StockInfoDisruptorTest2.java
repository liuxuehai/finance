package com.pi.service.sh;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pi.base.ExcelRequest;
import com.pi.base.HttpMethod;
import com.pi.common.Constants;
import com.pi.service.handler.StockInfoHandler;

public class StockInfoDisruptorTest2 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		StockInfoDisruptor2 stockInfoDisruptor = context.getBean(StockInfoDisruptor2.class);
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
		stockInfoDisruptor.publish(request);
		stockInfoDisruptor.publish(request);
		stockInfoDisruptor.publish(request);
		stockInfoDisruptor.publish(request);
		stockInfoDisruptor.publish(request);
	}
}
