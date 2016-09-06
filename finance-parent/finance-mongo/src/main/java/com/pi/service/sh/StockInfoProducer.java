package com.pi.service.sh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.WorkHandler;

@Component
@Scope("prototype")
public class StockInfoProducer implements WorkHandler<StockInfoEvent> {

	private Logger logger = LoggerFactory.getLogger(StockInfoConsumer.class);

	@Override
	public void onEvent(StockInfoEvent event) throws Exception {
		logger.info("事件:[{}]生产开始", event.toString());
	}

}
