package com.pi.service.sh;

import com.lmax.disruptor.ExceptionHandler;

public class StockInfoExceptionHandler implements ExceptionHandler<StockInfoEvent> {

	@Override
	public void handleEventException(Throwable arg0, long arg1, StockInfoEvent arg2) {
		
	}

	@Override
	public void handleOnShutdownException(Throwable arg0) {
		
	}

	@Override
	public void handleOnStartException(Throwable arg0) {
		
	}

}
