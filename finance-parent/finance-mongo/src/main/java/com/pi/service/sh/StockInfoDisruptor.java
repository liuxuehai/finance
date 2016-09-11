package com.pi.service.sh;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.pi.base.ExcelRequest;

@Component
public class StockInfoDisruptor implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	private Disruptor<StockInfoEvent> disruptor;
	private static final int NUM_EVENT_PROCESSORS = 3;
	private static final int BUFFER_SIZE = 1024 * 8;
	private final ExecutorService executor = Executors.newFixedThreadPool(NUM_EVENT_PROCESSORS,
			DaemonThreadFactory.INSTANCE);
	@Autowired
	private StockInfoEventHandler stockInfoEventHandler;

	private final RingBuffer<StockInfoEvent> ringBuffer = RingBuffer.createSingleProducer(new StockInfoEventFactory(),
			BUFFER_SIZE, new YieldingWaitStrategy());
	private final BatchEventProcessor<?>[] batchEventProcessors = new BatchEventProcessor[NUM_EVENT_PROCESSORS];
	private final StockInfoEventHandler[] handlers = new StockInfoEventHandler[NUM_EVENT_PROCESSORS];
	private final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		// EventFactory<StockInfoEvent> eventFactory = new
		// StockInfoEventFactory();
		// int ringBufferSize = 1024; // RingBuffer 大小，必须是 2 的 N 次方；
		// disruptor = new Disruptor<StockInfoEvent>(eventFactory,
		// ringBufferSize, Executors.defaultThreadFactory(),
		// ProducerType.SINGLE, new YieldingWaitStrategy());
		//
		// // EventHandler<StockInfoEvent> eventHandler = new
		// // StockInfoEventHandler();
		// disruptor.handleEventsWith(stockInfoEventHandler);
		//
		// disruptor.start();

		handlers[0] = applicationContext.getBean(StockInfoEventHandler.class);
		handlers[1] = applicationContext.getBean(StockInfoEventHandler.class);
		handlers[2] = applicationContext.getBean(StockInfoEventHandler.class);

		batchEventProcessors[0] = new BatchEventProcessor<StockInfoEvent>(ringBuffer, sequenceBarrier, handlers[0]);
		batchEventProcessors[1] = new BatchEventProcessor<StockInfoEvent>(ringBuffer, sequenceBarrier, handlers[1]);
		batchEventProcessors[2] = new BatchEventProcessor<StockInfoEvent>(ringBuffer, sequenceBarrier, handlers[2]);

		ringBuffer.addGatingSequences(batchEventProcessors[0].getSequence(), batchEventProcessors[1].getSequence(),
				batchEventProcessors[2].getSequence());
		for (int i = 0; i < NUM_EVENT_PROCESSORS; i++) {
			// handlers[i].reset(latch,
			// batchEventProcessors[i].getSequence().get() + ITERATIONS);
			executor.submit(batchEventProcessors[i]);
		}

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
