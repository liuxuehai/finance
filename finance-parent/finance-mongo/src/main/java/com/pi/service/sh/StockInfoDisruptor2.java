package com.pi.service.sh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.pi.base.ExcelRequest;

@Component
public class StockInfoDisruptor2 implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	private static final int NUM_EVENT_PROCESSORS = 3;
	private static final int NUM_EVENT_PRODUCER = 1;
	private static final int BUFFER_SIZE = 1024 * 8;

	private final RingBuffer<StockInfoEvent> ringBuffer = RingBuffer.createSingleProducer(new StockInfoEventFactory(),
			BUFFER_SIZE, new YieldingWaitStrategy());
	private final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

	private StockInfoProducer[] producer = new StockInfoProducer[NUM_EVENT_PRODUCER];
	private StockInfoConsumer[] consumer = new StockInfoConsumer[NUM_EVENT_PROCESSORS];

	@PostConstruct
	public void init() {

		for (int i = 0; i < NUM_EVENT_PRODUCER; i++) {
			producer[i] = applicationContext.getBean(StockInfoProducer.class);
		}

		WorkerPool<StockInfoEvent> crawler = new WorkerPool<StockInfoEvent>(ringBuffer, sequenceBarrier,
				new StockInfoExceptionHandler(), producer);
		SequenceBarrier sb = ringBuffer.newBarrier(crawler.getWorkerSequences());

		for (int i = 0; i < NUM_EVENT_PROCESSORS; i++) {
			consumer[i] = applicationContext.getBean(StockInfoConsumer.class);
		}
		WorkerPool<StockInfoEvent> applier = new WorkerPool<StockInfoEvent>(ringBuffer, sb,
				new StockInfoExceptionHandler(), consumer);
		List<Sequence> gatingSequences = new ArrayList<Sequence>();
		for (Sequence s : crawler.getWorkerSequences()) {
			gatingSequences.add(s);
		}
		for (Sequence s : applier.getWorkerSequences()) {
			gatingSequences.add(s);
		}
		ringBuffer.addGatingSequences(gatingSequences.toArray(new Sequence[gatingSequences.size()]));
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 7, 10, TimeUnit.MINUTES,
				new LinkedBlockingQueue<Runnable>(5));
		crawler.start(executor);
		applier.start(executor);

	}

	public void publish(ExcelRequest request) {
		// 发布事件；
		// RingBuffer<StockInfoEvent> ringBuffer = disruptor.getRingBuffer();
		long sequence = ringBuffer.next();// 请求下一个事件序号；

		try {
			StockInfoEvent event = ringBuffer.get(sequence);// 获取该序号对应的事件对象；
			// ExcelRequest request = getEventData();// 获取要通过事件传递的业务数据；
			event.setRequest(request);
		} finally {
			ringBuffer.publish(sequence);// 发布事件；
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
