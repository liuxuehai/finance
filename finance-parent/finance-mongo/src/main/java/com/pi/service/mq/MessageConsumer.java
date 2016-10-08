package com.pi.service.mq;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.pi.base.HttpMethod;
import com.pi.base.StockRequest;
import com.pi.service.handler.SinaDetailHandler;
import com.pi.service.header.HeaderBuilder;
import com.pi.service.processor.StockPriceProcessor;
import com.pi.service.stock.StockDisruptor;
import com.pi.stock.mq.Message;
import com.pi.stock.mq.StockPrice;

@Service
public class MessageConsumer implements ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
	@Autowired
	private StockDisruptor stockInfoDisruptor;
	ApplicationContext context;

//	@Async
	public void consumer(Message message) {
		if (message == null) {
			return;
		}

		if (message.getType().equalsIgnoreCase("price")) {
			StockPrice price = JSON.parseObject(message.getData().toString(), StockPrice.class);
			LocalDate today = LocalDate.parse(price.getEndDate());
			LocalDate start = LocalDate.parse(price.getStartDate());
			long s = start.until(today, ChronoUnit.DAYS);
			for (int i = 0; i <= s; i++) {

				DayOfWeek dayOfWeek = start.getDayOfWeek();
				if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
					try {
						Thread.sleep(2000);
						logger.info("发起请求日期:" + start.toString() + "code:" + price.getCode() + "序号:" + i);
						process(price.getCode(), start.toString());
					} catch (InterruptedException e) {
					}
				}
				start = start.plusDays(1);
			}
		}

	}

	private void process(String code, String date) {
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

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;

	}
}
