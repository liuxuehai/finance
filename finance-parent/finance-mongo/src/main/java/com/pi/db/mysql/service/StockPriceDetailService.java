package com.pi.db.mysql.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.stock.dao.StockPriceDetailDAO;
import com.pi.stock.model.StockPriceDetail;

@Service
public class StockPriceDetailService {
	private Logger logger = LoggerFactory.getLogger(StockPriceDetailService.class);
	@Autowired
	private StockPriceDetailDAO stockPriceDetailDAO;

	public void insertPriceBatch(List<StockPriceDetail> record) {
		if (record == null || record.size() <= 0) {
			return;
		}
		int size = record.size();
		int limit = 200;
		int start = 0;
		int end = start + limit;
		String tableName = "Price" + record.get(0).getCode();

		if (end > size) {
			stockPriceDetailDAO.insertPriceBatch(record, tableName);
		} else {
			int part = size / limit;
			logger.info("共有 [{}]条,! 分为 :[{}]批", size, part);

			for (int i = 0; i < part; i++) {
				List<StockPriceDetail> listPage = record.subList(0, limit);
				stockPriceDetailDAO.insertPriceBatch(listPage, tableName);
				record.subList(0, limit).clear();
			}
			if (!record.isEmpty()) {
				stockPriceDetailDAO.insertPriceBatch(record, tableName);
			}
		}

	}
}
