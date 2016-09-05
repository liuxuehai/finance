package com.pi.service.sh;

import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.pi.base.ExcelRequest;

@Component
public class StockInfoDisruptor {

	private Disruptor<StockInfoEvent> disruptor;
	@Autowired
	private StockInfoEventHandler stockInfoEventHandler;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		EventFactory<StockInfoEvent> eventFactory = new StockInfoEventFactory();
		int ringBufferSize = 1024 ; // RingBuffer 大小，必须是 2 的 N 次方；
		disruptor = new Disruptor<StockInfoEvent>(eventFactory, ringBufferSize, Executors.defaultThreadFactory(),
				ProducerType.SINGLE, new YieldingWaitStrategy());

		//EventHandler<StockInfoEvent> eventHandler = new StockInfoEventHandler();
		disruptor.handleEventsWith(stockInfoEventHandler);

		disruptor.start();
	}

	public void publish(ExcelRequest request) {
		// 发布事件；
		RingBuffer<StockInfoEvent> ringBuffer = disruptor.getRingBuffer();
		long sequence = ringBuffer.next();// 请求下一个事件序号；

		try {
			StockInfoEvent event = ringBuffer.get(sequence);// 获取该序号对应的事件对象；
			//ExcelRequest request = getEventData();// 获取要通过事件传递的业务数据；
			event.setRequest(request);
		} finally {
			ringBuffer.publish(sequence);// 发布事件；
		}
	}

}
