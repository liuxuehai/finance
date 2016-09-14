package com.pi.service.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.pi.base.BaseRequest;
import com.pi.service.sh.StockInfoConsumer;
import com.pi.stock.dao.StockInfoDAO;
import com.pi.stock.model.StockInfo;

@Component
public class StockInfoProcessor implements BaseProcessor<List<String>> {

	private Logger logger = LoggerFactory.getLogger(StockInfoConsumer.class);
	// @Autowired
	private MongoTemplate mongo;
	@Autowired
	private StockInfoDAO stockInfoDAO;

	@Override
	public void process(BaseRequest<List<String>> request, List<String> infos) {
		logger.info("处理下载结果开始,请求地址[{}],处理数量:[{}]", request.getUrl(), infos.size());
		for (String info : infos) {
			String[] temp = info.split("\t");
			StockInfo tempStock = stockInfoDAO.selectByCode(temp[0].trim());
			if (tempStock != null && tempStock.getId() > 0) {
				build(tempStock, temp, request.getParam().get("stockType"));
				stockInfoDAO.updateByPrimaryKey(tempStock);
			} else {
				StockInfo stock = new StockInfo();
				build(stock, temp, request.getParam().get("stockType"));
				stockInfoDAO.insert(stock);
			}
		}
	}

	private void build(StockInfo stock, String[] temp, String type) {
		if (type == "1") {
			stock.setCode(temp[0].trim());
			stock.setName(temp[1].trim());
			stock.setCodeA(temp[2].trim());
			stock.setNameA(temp[3].trim());
			stock.setDateA(temp[4].trim());
			stock.setgCapitalA(temp[5].trim());
			stock.setfCapitalA(temp[6].trim());
		} else if (type == "2") {
			stock.setCode(temp[0].trim());
			stock.setName(temp[1].trim());
			stock.setCodeB(temp[2].trim());
			stock.setNameB(temp[3].trim());
			stock.setDateB(temp[4].trim());
			stock.setgCapitalB(temp[5].trim());
			stock.setfCapitalB(temp[6].trim());
		}
	}

}
