package com.finance.component.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.finance.component.client.FinanceClient;

@Component
public class PriceQueueProcessor implements ApplicationContextAware {
	private Logger logger = LoggerFactory.getLogger(PriceQueueProcessor.class);
	@Autowired
	FinanceClient financeClient;
	ApplicationContext context;
	private static final int NUM_EVENT_PROCESSORS = 10;
	private static final int MAX_POOL_SIZE = 10;
	private static final long KEEP_ALIVE_TIME = 3;
	private static final int CAPACITY = 1024;
	public static final int BUFFER_SIZE = 1024;
	// private final ExecutorService executor =
	// Executors.newFixedThreadPool(NUM_EVENT_PROCESSORS);
	private final BlockingQueue<String> blockingQueues = new LinkedBlockingQueue<String>(BUFFER_SIZE);
	private final PriceQueueThread[] queueProcessors = new PriceQueueThread[NUM_EVENT_PROCESSORS];
	ThreadPoolExecutor executor = new ThreadPoolExecutor(NUM_EVENT_PROCESSORS, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
			TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(CAPACITY));

	@PostConstruct
	public void init() {
		for (int i = 0; i < NUM_EVENT_PROCESSORS; i++) {
			queueProcessors[i] = new PriceQueueThread(blockingQueues, financeClient, context);
			executor.submit(queueProcessors[i]);
		}

	}

	public void publish(String url) {
		try {
			blockingQueues.put(url);
		} catch (InterruptedException e) {
			logger.error("put url 被打断:{}", e);
		}
	}

	public int getSize() {
		return blockingQueues.size();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

}
