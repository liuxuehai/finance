package com.pi.service.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.WorkHandler;
import com.pi.service.client.CommonClient;

@Component
@Scope("prototype")
public class StockConsumer implements WorkHandler<StockEvent> {

	private Logger logger = LoggerFactory.getLogger(StockConsumer.class);
	@Autowired
	CommonClient commonClient;

	@Override
	public void onEvent(StockEvent event) throws Exception {
		logger.info("股票消费事件:[{}]处理开始", event.getRequest().getUrl());
		try {
			commonClient.get(event.getRequest());
		} catch (Exception e) {
			logger.error("股票消费事件:[{}]处理出错,错误:[{}]", event.getRequest().getUrl(), e);
		}
	}

}
