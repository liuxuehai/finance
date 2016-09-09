package com.pi.service.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.pi.base.BaseRequest;
import com.pi.stock.model.StockPriceDetail;

@Component
public class StockPriceProcessor implements BaseProcessor<List<String>> {

	private Logger logger = LoggerFactory.getLogger(StockPriceProcessor.class);
	@Autowired
	private MongoTemplate mongo;

	@Override
	public void process(BaseRequest<List<String>> request, List<String> infos) {
		logger.info("处理下载结果开始,请求地址[{}],处理数量:[{}]", request.getUrl(), infos.size());
		
		if(infos.size()<=0){
			return ;
		}
		
		List<StockPriceDetail> prices = new ArrayList<StockPriceDetail>();
		String date = request.getParam().get("date");
		String symbol = request.getParam().get("symbol").substring(2);
		for (String info : infos) {
			String[] temp = info.split("\t");
			StockPriceDetail price = new StockPriceDetail();
			price.setCode(symbol);
			price.setDate(date);
			price.setDateTime(temp[0].trim());
			price.setPrice(temp[1].trim());
			price.setPriceM(temp[2].trim());
			price.setVolume(temp[3].trim());
			price.setTurnover(temp[4].trim());
			price.setType(temp[5].trim());
			prices.add(price);
		}

		//mongo.insertAll(prices);
	}

}
