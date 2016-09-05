package com.pi.service.sh;

import com.lmax.disruptor.EventFactory;

public class StockInfoEventFactory implements EventFactory<StockInfoEvent> {

	@Override
	public StockInfoEvent newInstance() {
		return new StockInfoEvent();
	}

}
