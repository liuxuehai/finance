package com.pi.stock.dao;

import com.pi.stock.model.StockPriceDetail;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceDetailDAO {
	int insert(StockPriceDetail record);

	StockPriceDetail selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(StockPriceDetail record);

	int insertPriceBatch(List<StockPriceDetail> record);
}