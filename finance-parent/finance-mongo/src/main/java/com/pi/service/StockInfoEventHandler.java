package com.pi.service.sh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.EventHandler;
import com.pi.service.client.ExcelClient;

@Component
public class StockInfoEventHandler implements EventHandler<StockInfoEvent> {

	private Logger logger = LoggerFactory.getLogger(StockInfoEventHandler.class);
	@Autowired
	ExcelClient excelClient;
	
	@Override
	public void onEvent(StockInfoEvent event, long sequence, boolean endOfBatch) throws Exception {
		logger.info("事件:[{}]处理开始,序号为:[{}]",event.toString(),sequence);
		try {
			excelClient.get(event.getRequest());
		} catch (Exception e) {
			logger.error("事件:[{}]处理出错,错误:[{}]",event.toString(),e);
		}
	}

}
