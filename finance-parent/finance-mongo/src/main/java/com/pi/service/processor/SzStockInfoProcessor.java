package com.pi.service.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.pi.base.BaseRequest;
import com.pi.stock.model.StockInfo;

@Component
public class SzStockInfoProcessor implements BaseProcessor<List<String>> {

	private Logger logger = LoggerFactory.getLogger(SzStockInfoProcessor.class);
	@Autowired
	private MongoTemplate mongo;
	
	@Override
	public void process(BaseRequest<List<String>> request, List<String> infos) {
		logger.info("处理下载结果开始,请求地址[{}],处理数量:[{}]", request.getUrl(), infos.size());
		List<StockInfo> stockInfos = new ArrayList<StockInfo>();

		for (String info : infos) {
			String[] temp = info.split("\t");
			StockInfo stock = new StockInfo();
			stock.setCode(temp[0].trim());
			stock.setName(temp[1].trim());
			stock.setFullName(temp[2].trim());
			stock.setEnName(temp[3].trim());
			stock.setAddress(temp[4].trim());
			stock.setCodeA(temp[5].trim());
			stock.setNameA(temp[6].trim());
			stock.setDateA(temp[7].trim());
			stock.setgCapitalA(temp[8].trim());
			stock.setfCapitalA(temp[9].trim());
			stock.setCodeB(temp[10].trim());
			stock.setNameB(temp[11].trim());
			stock.setDateB(temp[12].trim());
			stock.setgCapitalB(temp[13].trim());
			stock.setfCapitalB(temp[14].trim());
			stock.setArea(temp[15].trim());
			stock.setProvince(temp[16].trim());
			stock.setCity(temp[17].trim());
			stock.setIndustry(temp[18].trim());
			stock.setWebsite(temp[19].trim());
			stockInfos.add(stock);
		}
		
		//mongo.insertAll(stockInfos);
		
	}

}
