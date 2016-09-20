package com.pi.service.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.ExceptionHandler;

public class StockExceptionHandler implements ExceptionHandler<StockEvent> {
	private Logger logger = LoggerFactory.getLogger(StockExceptionHandler.class);
	@Override
	public void handleEventException(Throwable ex, long sequence, StockEvent event) {
		ex.printStackTrace();
		logger.error("处理中错误[{}],[{}]",sequence,event);
	}

	@Override
	public void handleOnStartException(Throwable ex) {
		logger.error("开始处理时错误[{}]",ex);
		
	}

	@Override
	public void handleOnShutdownException(Throwable ex) {
		logger.error("关闭处理时错误[{}]",ex);
	}

}
