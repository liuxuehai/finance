package com.finance.service.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.finance.pojo.model.db.StockPriceDetail;

@Component
public class StockPriceDao {

	private Logger logger = LoggerFactory.getLogger(StockPriceDao.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void batchInsert(List<StockPriceDetail> prices) {
		if (prices == null || prices.size() <= 0) {
			return;
		}
		int size = prices.size();
		int limit = 200;
		int start = 0;
		int end = start + limit;
		String tableName = "Price" + prices.get(0).getCode();
		if (end > size) {
			insertPriceBatch(prices, tableName);
		} else {
			int part = size / limit;
			logger.info("共有 [{}]条,! 分为 :[{}]批", size, part);

			for (int i = 0; i < part; i++) {
				List<StockPriceDetail> listPage = prices.subList(0, limit);
				insertPriceBatch(listPage, tableName);
				prices.subList(0, limit).clear();
			}
			if (!prices.isEmpty()) {
				insertPriceBatch(prices, tableName);
			}
		}

	}

	private void insertPriceBatch(List<StockPriceDetail> prices, String tableName) {
		if (prices == null || prices.size() <= 0) {
			return;
		}
		final String sql = "insert into " + tableName
				+ "(code, date,dateTime, price, priceChange,volume,turnover, type,createTime)" + "values ( ?,?,?,?,"
				+ "?,?,?,?,now())";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				String code = prices.get(i).getCode();
				String date = prices.get(i).getDate();
				String dateTime = prices.get(i).getDateTime();
				String price = prices.get(i).getPrice();
				String priceChange = prices.get(i).getPriceChange();
				String volume = prices.get(i).getVolume();
				String turnover = prices.get(i).getTurnover();
				String type = prices.get(i).getType();
				ps.setString(1, code);
				ps.setString(2, date);
				ps.setString(3, dateTime);
				ps.setString(4, price);
				ps.setString(5, priceChange);
				ps.setString(6, volume);
				ps.setString(7, turnover);
				ps.setString(8, type);

			}

			@Override
			public int getBatchSize() {
				return prices.size();
			}
		});
	}
}
