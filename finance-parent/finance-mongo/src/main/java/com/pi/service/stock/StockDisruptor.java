package com.pi.service.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.pi.base.StockRequest;

@Component
public class StockDisruptor implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	private Logger logger = LoggerFactory.getLogger(StockDisruptor.class);
	private static final int NUM_EVENT_PROCESSORS = 1;
	private static final int NUM_EVENT_PRODUCER = 1;
	private static final int BUFFER_SIZE = 1024*1024 ;

	private static final int CORE_POOL_SIZE = 2;
	private static final int MAX_POOL_SIZE = 2;
	private static final long KEEP_ALIVE_TIME = 3;
	private static final int CAPACITY = 1024;

	private final RingBuffer<StockEvent> ringBuffer = RingBuffer.createSingleProducer(new StockEventFactory(),
			BUFFER_SIZE, new YieldingWaitStrategy());
	private final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

	private StockProducer[] producer = new StockProducer[NUM_EVENT_PRODUCER];
	private StockConsumer[] consumer = new StockConsumer[NUM_EVENT_PROCESSORS];

	@PostConstruct
	public void init() {

		for (int i = 0; i < NUM_EVENT_PRODUCER; i++) {
			producer[i] = applicationContext.getBean(StockProducer.class);
		}

		WorkerPool<StockEvent> crawler = new WorkerPool<StockEvent>(ringBuffer, sequenceBarrier,
				new StockExceptionHandler(), producer);
		SequenceBarrier sb = ringBuffer.newBarrier(crawler.getWorkerSequences());

		for (int i = 0; i < NUM_EVENT_PROCESSORS; i++) {
			consumer[i] = applicationContext.getBean(StockConsumer.class);
		}
		WorkerPool<StockEvent> applier = new WorkerPool<StockEvent>(ringBuffer, sb, new StockExceptionHandler(),
				consumer);
		List<Sequence> gatingSequences = new ArrayList<Sequence>();
		for (Sequence s : crawler.getWorkerSequences()) {
			gatingSequences.add(s);
		}
		for (Sequence s : applier.getWorkerSequences()) {
			gatingSequences.add(s);
		}
		ringBuffer.addGatingSequences(gatingSequences.toArray(new Sequence[gatingSequences.size()]));
		ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
				TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(CAPACITY));
		crawler.start(executor);
		applier.start(executor);

	}

	public void publish(StockRequest request) {
		// 发布事件
		long sequence = ringBuffer.next();// 请求下一个事件序号
		logger.info("获取股票事件序号:[{}]", sequence);

		try {
			StockEvent event = ringBuffer.get(sequence);// 获取该序号对应的事件对象
			event.setRequest(request);
		} finally {
			logger.info("发布股票事件序号:[{}]", sequence);
			ringBuffer.publish(sequence);// 发布事件
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
