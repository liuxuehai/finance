package com.pi.service.sh;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pi.base.ExcelRequest;
import com.pi.base.HttpMethod;
import com.pi.common.Constants;
import com.pi.service.handler.StockInfoHandler;
import com.pi.service.header.HeaderBuilder;

public class StockInfoDisruptorTest2 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		StockInfoDisruptor2 stockInfoDisruptor = context.getBean(StockInfoDisruptor2.class);
		String url = "http://query.sse.com.cn/security/stock/downloadStockListFile.do?stockType=1";
		ExcelRequest request = new ExcelRequest();
		request.setUrl(url);
		Map<String, String> param = new HashMap<String, String>();
		StockInfoHandler sinaDetailHandle = new StockInfoHandler();

		request.setHeader(HeaderBuilder.getHeader(HeaderBuilder.SHANGHAI));

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
