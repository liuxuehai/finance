package com.pi.service.sh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lmax.disruptor.EventHandler;
import com.pi.service.client.ExcelClient;

@Component
@Scope("prototype")
public class StockInfoEventHandler implements EventHandler<StockInfoEvent> {

	private Logger logger = LoggerFactory.getLogger(StockInfoEventHandler.class);
	@Autowired
	ExcelClient excelClient;

	@Override
	public void onEvent(StockInfoEvent event, long sequence, boolean endOfBatch) throws Exception {
		logger.info("股票信息事件:[{}]处理开始,序号为:[{}]", event.getRequest().getUrl(), sequence);
		try {
			excelClient.get(event.getRequest());
		} catch (Exception e) {
			logger.error("股票信息事件:[{}]处理出错,错误:[{}]", event.getRequest().getUrl(), e);
		}
	}

}
