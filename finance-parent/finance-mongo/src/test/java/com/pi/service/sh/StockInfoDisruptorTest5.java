package com.pi.service.sh;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pi.base.ExcelRequest;
import com.pi.base.HttpMethod;
import com.pi.service.handler.StockInfoHandler;
import com.pi.service.header.HeaderBuilder;
import com.pi.service.processor.StockInfoProcessor;
import com.pi.service.processor.StockPriceProcessor;

public class StockInfoDisruptorTest5 {

	private static Logger logger = LoggerFactory.getLogger(StockInfoDisruptorTest5.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		StockInfoDisruptor2 stockInfoDisruptor = context.getBean(StockInfoDisruptor2.class);
		// String url =
		// "http://market.finance.sina.com.cn/downxls.php?date=2016-08-24&symbol=sh601766";
		// String url =
		// "http://market.finance.sina.com.cn/downxls.php?date=2016-09-09&symbol=sh600021";
		ExcelRequest request;

		// 2004-10-8
		LocalDate today = LocalDate.now();
		LocalDate start = LocalDate.of(2004, 10, 7);
		long s = start.until(today, ChronoUnit.DAYS);
		for (int i = 0; i <= s; i++) {
			start = start.plusDays(1);
			DayOfWeek dayOfWeek = start.getDayOfWeek();
			if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
				if (i % 10 == 0) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				String host = "http://market.finance.sina.com.cn/downxls.php";
				String date = start.toString();
				String symbol = "600094";
				String url = host + "?date=" + date + "&symbol=sh" + symbol;
				logger.info("处理地址" + url);
				request = new ExcelRequest();
				Map<String, String> param = new HashMap<String, String>();

				param.put("date", date);
				param.put("symbol", "sh600094");
				StockInfoHandler sinaDetailHandle = new StockInfoHandler();
				sinaDetailHandle.setRemoveTitle(true);
				request.setHeader(HeaderBuilder.getHeader(HeaderBuilder.SINA));

				request.setResponseHandler(sinaDetailHandle);

				request.setHttpMethod(HttpMethod.GET);
				request.setProcessor(context.getBean(StockPriceProcessor.class));
				request.setParam(param);
				request.setUrl(url);
				logger.info("请求地址" + request.getUrl());
				 stockInfoDisruptor.publish(request);
			}
		}

	}
}
