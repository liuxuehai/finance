package com.pi.stock.dao;

import com.pi.stock.model.StockPriceDetail;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceDetailDAO {
	int insert(@Param("record")StockPriceDetail record,@Param("tableName")String tableName);

	StockPriceDetail selectByPrimaryKey(@Param("id")Integer id,@Param("tableName")String tableName);

	int updateByPrimaryKey(@Param("record")StockPriceDetail record,@Param("tableName")String tableName);

	int insertPriceBatch(@Param("record")List<StockPriceDetail> record,@Param("tableName")String tableName);
}