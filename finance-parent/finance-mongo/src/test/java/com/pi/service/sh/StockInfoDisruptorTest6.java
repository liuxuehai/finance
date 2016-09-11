package com.pi.service.sh;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pi.base.ExcelRequest;
import com.pi.base.HttpMethod;
import com.pi.service.handler.StockInfoHandler;
import com.pi.service.header.HeaderBuilder;
import com.pi.service.processor.StockPriceProcessor;
import com.pi.stock.model.StockInfo;

public class StockInfoDisruptorTest6 {

	private static Logger logger = LoggerFactory.getLogger(StockInfoDisruptorTest6.class);
	static ApplicationContext context;
	static StockInfoDisruptor2 stockInfoDisruptor;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");

		MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
		stockInfoDisruptor = context.getBean(StockInfoDisruptor2.class);
		
		Query query = new Query();
//		List<String> nio = new ArrayList<String>();
//		nio.add("600094");
//		nio.add("600054");
//		nio.add("600190");
//		nio.add("600221");
//		nio.add("600272");
//		nio.add("600295");
//		nio.add("600320");
//		nio.add("600555");
//		nio.add("600602");
//		nio.add("600604");
		//query.addCriteria(Criteria.where("dateA").lt("2004-10-8"));
		List<String> in = new ArrayList<String>();
		in.add("600610");
		in.add("600611");
		
		query.addCriteria(Criteria.where("code").in(in));

		List<StockInfo> infos = mongoTemplate.find(query, StockInfo.class);
		for (StockInfo stockInfo : infos) {
			fentch(stockInfo);
		}
	}

	private static void fentch(StockInfo stockInfo) {
		ExcelRequest request;

		// 2004-10-8
		LocalDate today = LocalDate.now();
		LocalDate start = LocalDate.of(2004, 10, 7);
		long s = start.until(today, ChronoUnit.DAYS);
		for (int i = 0; i <= s; i++) {
			start = start.plusDays(1);
			DayOfWeek dayOfWeek = start.getDayOfWeek();
			if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
				if (i!=0&&i % 10 == 0) {
					try {
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				String host = "http://market.finance.sina.com.cn/downxls.php";
				String date = start.toString();
				String code = stockInfo.getCode();
				String symbol = "";
				if (code.startsWith("6")) {
					symbol = "sh" + code;
				} else {
					symbol = "sz" + code;
				}
				String url = host + "?date=" + date + "&symbol=" + symbol;
				logger.info("处理地址" + url);
				request = new ExcelRequest();
				Map<String, String> param = new HashMap<String, String>();
				param.put("date", date);
				param.put("symbol", symbol);
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
		try {
			Thread.sleep(10*60*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
