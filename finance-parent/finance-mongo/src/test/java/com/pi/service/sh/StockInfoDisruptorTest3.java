package com.pi.service.sh;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pi.base.ExcelRequest;
import com.pi.base.HttpMethod;
import com.pi.service.handler.StockInfoHandler;
import com.pi.service.header.HeaderBuilder;
import com.pi.service.processor.StockInfoProcessor;

public class StockInfoDisruptorTest3 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		StockInfoDisruptor2 stockInfoDisruptor = context.getBean(StockInfoDisruptor2.class);
		String url = "http://query.sse.com.cn/security/stock/downloadStockListFile.do?stockType=1";
		ExcelRequest request = new ExcelRequest();
		request.setUrl(url);
		Map<String, String> param = new HashMap<String, String>();
		param.put("stockType", "1");
		StockInfoHandler sinaDetailHandle = new StockInfoHandler();
		sinaDetailHandle.setRemoveTitle(true);
		request.setHeader(HeaderBuilder.getHeader(HeaderBuilder.SHANGHAI));

		request.setResponseHandler(sinaDetailHandle);
		request.setParam(param);
		request.setHttpMethod(HttpMethod.GET);
		request.setProcessor(context.getBean(StockInfoProcessor.class));
		stockInfoDisruptor.publish(request);
		
		
		ExcelRequest request2 = new ExcelRequest();
		String url2 = "http://query.sse.com.cn/security/stock/downloadStockListFile.do?stockType=2";
		request2.setUrl(url2);
		Map<String, String> param2 = new HashMap<String, String>();
		StockInfoHandler sinaDetailHandle2 = new StockInfoHandler();
		sinaDetailHandle2.setRemoveTitle(true);
		request2.setHeader(HeaderBuilder.getHeader(HeaderBuilder.SHANGHAI));
		param2.put("stockType", "2");
		request2.setResponseHandler(sinaDetailHandle2);
		request2.setParam(param2);
		request2.setHttpMethod(HttpMethod.GET);
		request2.setProcessor(context.getBean(StockInfoProcessor.class));
		stockInfoDisruptor.publish(request2);
		
	}
}
