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
import com.pi.stock.dao.StockInfoDAO;
import com.pi.stock.model.StockInfo;

public class StockInfoDisruptorTest6 {

	private static Logger logger = LoggerFactory.getLogger(StockInfoDisruptorTest6.class);
	static ApplicationContext context;
	static StockInfoDisruptor2 stockInfoDisruptor;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");

		StockInfoDAO stockInfoDAO = context.getBean(StockInfoDAO.class);
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
		//in.add("600054");
		//in.add("600190");
		//in.add("600221");
		//in.add("600272");
//		in.add("600295");
//		in.add("600320");
//		in.add("600555");
//		in.add("600602");
//		in.add("600604");
//		in.add("600610");
//		in.add("600611");
//		in.add("600612");
//		in.add("600613");
		//in.add("600614");
		//in.add("600617");
//		in.add("600618");
//		in.add("600619");
//		in.add("600623");
//		in.add("600639");
//		in.add("600648");
//		in.add("600650");
//		in.add("600663");
//		in.add("600679");
//		in.add("600680");
//		in.add("600689");
//		in.add("600695");
//		in.add("600698");
//		in.add("600726");
//		in.add("600751");
//		in.add("600754");
//		in.add("600776");
//		in.add("600801");
//		in.add("600818");
//		in.add("600819");
//		in.add("600822");
//		in.add("600827");
//		in.add("600835");
//		in.add("600841");
//		in.add("600843");
//		in.add("600844");
//		
//		in.add("600845");
//		in.add("600848");
//		in.add("600851");
//		in.add("600000");
//		in.add("600004");
//		in.add("600007");
		
		in.add("600005");
		in.add("600006");
		in.add("600008");
		in.add("600009");
		in.add("600010");
		in.add("600011");
		
		//query.addCriteria(Criteria.where("code").in(in));
		
		for (String string : in) {
			StockInfo stockInfo =stockInfoDAO.selectByCode(string);
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
				if (i!=0&&i % 3 == 0) {
					try {
						Thread.sleep(10000);
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
