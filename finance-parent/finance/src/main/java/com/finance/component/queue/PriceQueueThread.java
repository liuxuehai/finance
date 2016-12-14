package com.finance.component.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.finance.component.client.FinanceClient;
import com.finance.pojo.handler.SinaDetailHandler;
import com.finance.pojo.headler.HeaderBuilder;
import com.finance.pojo.request.HttpMethod;
import com.finance.pojo.request.StockRequest;
import com.finance.service.processor.StockPriceProcessor;

public class PriceQueueThread implements Runnable {
	private Logger logger = LoggerFactory.getLogger(PriceQueueThread.class);
	private volatile boolean running;
	private BlockingQueue<String> blockingQueue;
	FinanceClient financeClient;
	ApplicationContext context;

	PriceQueueThread(BlockingQueue<String> blockingQueue, FinanceClient financeClient,
			ApplicationContext applicationContext) {
		this.blockingQueue = blockingQueue;
		this.financeClient = financeClient;
		this.context = applicationContext;
	}

	@Override
	public void run() {
		running = true;
		while (true) {
			try {
				String value = blockingQueue.take();
				Map<String, String> param = buildString(value);
				StockRequest request = new StockRequest();
				SinaDetailHandler sinaDetailHandle = new SinaDetailHandler();
				sinaDetailHandle.setRemoveTitle(true);
				request.setHeader(HeaderBuilder.getHeader(HeaderBuilder.SINA));
				request.setResponseHandler(sinaDetailHandle);
				request.setHttpMethod(HttpMethod.GET);
				request.setProcessor(context.getBean(StockPriceProcessor.class));
				request.setParam(param);
				request.setUrl(value);
				try {
					financeClient.get(request);
				} catch (Exception e) {
					logger.error("股票消费事件:[{}]处理出错,错误:[{}]", value, e);
				}
			} catch (InterruptedException ex) {
				logger.error("消费任务处理出错:{}",ex);
				if (!running) {
					break;
				}
			}
		}
	}

	private Map<String, String> buildString(String url) {
		Map<String, String> param = new HashMap<String, String>();
		String data = url.split("[?]")[1];
		String[] datas = data.split("&");
		for (String temp : datas) {
			String[] temps = temp.split("=");
			param.put(temps[0], temps[1]);
		}

		return param;
	}
}
