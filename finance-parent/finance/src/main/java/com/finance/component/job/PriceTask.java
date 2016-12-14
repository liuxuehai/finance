package com.finance.component.job;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.finance.component.queue.PriceQueueProcessor;
import com.finance.service.redis.FinanceRedis;

@Component
public class PriceTask implements Runnable {
	private volatile boolean running;
	private Logger logger = LoggerFactory.getLogger(PriceTask.class);
	@Autowired
	FinanceRedis financeRedis;
	@Autowired
	PriceQueueProcessor priceQueueProcessor;
	String key = "finance.stock.price";

	private final static int batchSize = 100;
	private final static int maxSize = PriceQueueProcessor.BUFFER_SIZE;

	AtomicInteger sleepTime = new AtomicInteger(1);
	int time = sleepTime.get();
	private final static int minTime = 1;
	private final static int maxTime = 10;

	@Override
	public void run() {
		running = true;
		while (true) {
			try {
				int currentSize = priceQueueProcessor.getSize();
				long dataSize = financeRedis.llen(key);
				time = sleepTime.get();
				logger.info("队列大小:" + currentSize + " 数据大小:" + dataSize + "  当前sleep时间:" + sleepTime.get());
				if (currentSize + batchSize > maxSize || dataSize <= 0) {
//					if (sleepTime.get() < maxTime) {
//						time = sleepTime.incrementAndGet();
//					} else {
//						time = sleepTime.get();
//					}
				} else {
					getData();
//					if (sleepTime.get() > minTime) {
//						time = sleepTime.decrementAndGet();
//					} else {
//						time = sleepTime.get();
//					}
				}
				logger.info("处理后sleep时间:" + sleepTime.get());
				Thread.sleep(time * 60000);
			} catch (InterruptedException e) {
				logger.error("定时任务处理出错:{}", e);
				if (!running) {
					break;
				}
			}
		}
	}

	private void getData() {
		for (int i = 0; i < batchSize; i++) {
			try {
				String url = financeRedis.lpop(key);
				logger.info("redis url:" + url);
				if(!StringUtils.isEmpty(url)){
					priceQueueProcessor.publish(url);
				}
			} catch (Exception e) {
				logger.error("处理数据出错:{}", e);
			}
		}
	}
}
