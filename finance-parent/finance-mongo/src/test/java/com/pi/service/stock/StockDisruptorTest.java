package com.pi.service.stock;

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

import com.pi.base.HttpMethod;
import com.pi.base.StockRequest;
import com.pi.service.handler.SinaDetailHandler;
import com.pi.service.header.HeaderBuilder;
import com.pi.service.processor.StockPriceProcessor;
import com.pi.service.sh.StockInfoDisruptorTest6;
import com.pi.stock.dao.StockInfoDAO;
import com.pi.stock.model.StockInfo;

public class StockDisruptorTest {
	private static Logger logger = LoggerFactory.getLogger(StockInfoDisruptorTest6.class);
	static ApplicationContext context;
	static StockDisruptor stockInfoDisruptor;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
		StockInfoDAO stockInfoDAO = context.getBean(StockInfoDAO.class);
		stockInfoDisruptor = context.getBean(StockDisruptor.class);

		List<String> in = new ArrayList<String>();

//		in.add("600012");
//		in.add("600015");
//		in.add("600016");
//		in.add("600019");
//		in.add("600020");
//		in.add("600021");
//		
//		in.add("600022");
//		in.add("600026");
//		in.add("600028");
		
		//in.add("600029");
//		in.add("600030");
//		in.add("600031");
//		
//		in.add("600033");
//		in.add("600035");
//		in.add("600036");
//		in.add("600037");
//		in.add("600038");
//		in.add("600039");
		
		
		in.add("600050");
		in.add("600051");
		in.add("600052");
		in.add("600053");
		in.add("600055");
		in.add("600056");
		in.add("600057");
		in.add("600058");
		in.add("600059");
		in.add("600060");
		in.add("600061");

		for (String string : in) {
			StockInfo stockInfo = stockInfoDAO.selectByCode(string);
			fentch(stockInfo);
		}
	}

	private static void process(String code, String date) {
		String host = "http://market.finance.sina.com.cn/downxls.php";
		String symbol = "";
		if (code.startsWith("6")) {
			symbol = "sh" + code;
		} else {
			symbol = "sz" + code;
		}
		String url = host + "?date=" + date + "&symbol=" + symbol;
		logger.info("处理地址" + url);
		StockRequest request = new StockRequest();
		Map<String, String> param = new HashMap<String, String>();
		param.put("date", date);
		param.put("symbol", symbol);
		SinaDetailHandler sinaDetailHandle = new SinaDetailHandler();
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

	private static void fentch(StockInfo stockInfo) {

		// 2004-10-8
		LocalDate today = LocalDate.now();
		LocalDate start = LocalDate.of(2004, 10, 7);
		long s = start.until(today, ChronoUnit.DAYS);
		for (int i = 0; i <= s; i++) {
			start = start.plusDays(1);
			
			DayOfWeek dayOfWeek = start.getDayOfWeek();
			if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
//				if (i != 0 && i % 3 == 0) {
//					try {
//						Thread.sleep(10000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				logger.info("发起请求日期:" + start.toString()+"code:"+stockInfo.getCode()+"序号:"+i);
				process(stockInfo.getCode(), start.toString());
			}
		}
		try {
			Thread.sleep(10 * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}