package com.pi.stock.dao;

import com.pi.stock.model.StockInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInfoDAO {
	int insert(StockInfo record);

	StockInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(StockInfo record);

	StockInfo selectByCode(String code);
}