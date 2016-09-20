package com.pi.service.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.WorkHandler;

@Component
@Scope("prototype")
public class StockProducer implements WorkHandler<StockEvent> {

	private Logger logger = LoggerFactory.getLogger(StockProducer.class);

	@Override
	public void onEvent(StockEvent event) throws Exception {
		logger.info("生产股票事件:[{}]生产开始", event.getRequest().getUrl());
	}

}
