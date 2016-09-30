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
		
//		in.add("600059");
//		in.add("600060");
//		in.add("600061");
//		in.add("600062");
//		in.add("600063");
//		in.add("600064");
//		
//		in.add("600066");
//		in.add("600067");
//		in.add("600068");
//		in.add("600069");
//		in.add("600070");
//		in.add("600071");
//		
//		
//		in.add("600072");
//		in.add("600073");
//		in.add("600074");
//		in.add("600075");
//		in.add("600076");
//		in.add("600077");
//		
//		in.add("600078");
//		in.add("600079");
//		in.add("600080");
//		in.add("600081");
//		in.add("600082");
//		in.add("600083");
//		
//		in.add("600084");
//		in.add("600085");
//		in.add("600086");
		
		
		in.add("600088");
		in.add("600089");
		
		in.add("600090");
		in.add("600091");
		in.add("600093");
		in.add("600095");
		
		
		
		in.add("600096");
		in.add("600097");
		in.add("600098");
		in.add("600099");
		in.add("600100");
		in.add("600101");
		
		in.add("600103");
		in.add("600104");
		in.add("600105");
		in.add("600106");
		in.add("600107");
		in.add("600108");
		in.add("600109");
		in.add("600110");
		
		in.add("600112");
		in.add("600113");
		in.add("600114");
		in.add("600115");
		in.add("600116");
		in.add("600117");
		in.add("600118");
		in.add("600119");
		
		
		in.add("600120");
		in.add("600121");
		in.add("600122");
		in.add("600123");
		in.add("600124");
		in.add("600125");
		in.add("600126");
		in.add("600127");
		
		in.add("600128");
		in.add("600129");
		in.add("600130");
		in.add("600131");
		in.add("600132");
		in.add("600133");
		in.add("600135");
		in.add("600136");
		
		in.add("600137");
		in.add("600138");
		in.add("600139");
		in.add("600141");
		in.add("600143");
		in.add("600145");
		
		
		in.add("600146");
		in.add("600148");
		in.add("600149");
		in.add("600150");
		in.add("600151");
		in.add("600152");
		in.add("600153");
		
		in.add("600155");
		in.add("600156");
		in.add("600157");
		in.add("600158");
		in.add("600159");
		
		in.add("600160");
		in.add("600161");
		in.add("600162");
		in.add("600163");
		in.add("600165");
		in.add("600166");
		in.add("600167");
		in.add("600168");
		in.add("600169");
		in.add("600170");
		
		in.add("600171");
		in.add("600172");
		in.add("600173");
		in.add("600175");
		in.add("600176");
		in.add("600177");
		in.add("600178");
		in.add("600179");
		in.add("600180");
		in.add("600182");
		
		in.add("600183");
		in.add("600184");
		in.add("600185");
		in.add("600186");
		in.add("600187");
		in.add("600188");
		in.add("600189");
		in.add("600191");

		for (String string : in) {
			StockInfo stockInfo = stockInfoDAO.selectByCode(string);
			if(stockInfo!=null){
				fentch(stockInfo);
			}
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
		LocalDate today = LocalDate.of(2016, 9, 30);
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
					logger.info("发起请求日期:" + start.toString()+"code:"+stockInfo.getCode()+"序号:"+i);
					process(stockInfo.getCode(), start.toString());
				} catch (InterruptedException e) {
				}
			}
		}
		try {
			Thread.sleep(10 * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
