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
import com.pi.service.processor.StockPriceProcessor;

public class StockInfoDisruptorTest4 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		StockInfoDisruptor2 stockInfoDisruptor = context.getBean(StockInfoDisruptor2.class);
		String url = "http://market.finance.sina.com.cn/downxls.php?date=2016-08-24&symbol=sh601766";
		ExcelRequest request = new ExcelRequest();
		request.setUrl(url);
		Map<String, String> param = new HashMap<String, String>();
		param.put("date", "2016-08-24");
		param.put("symbol", "sh601766");
		StockInfoHandler sinaDetailHandle = new StockInfoHandler();
		sinaDetailHandle.setRemoveTitle(true);
		request.setHeader(HeaderBuilder.getHeader(HeaderBuilder.SINA));

		request.setResponseHandler(sinaDetailHandle);
		request.setParam(param);
		request.setHttpMethod(HttpMethod.GET);
		request.setProcessor(context.getBean(StockPriceProcessor.class));
		stockInfoDisruptor.publish(request);
		
		
	}
}
