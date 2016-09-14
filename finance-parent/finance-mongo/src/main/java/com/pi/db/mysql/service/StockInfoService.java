package com.pi.db.mysql.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.stock.dao.StockInfoDAO;
import com.pi.stock.model.StockInfo;

@Service
public class StockInfoService {
	@Autowired
	private StockInfoDAO stockInfoDAO;

	public void addOrUpdate(List<StockInfo> stockInfos) {

		if (stockInfos == null || stockInfos.size() <= 0) {
			return;
		}
		for (StockInfo stockInfo : stockInfos) {
			StockInfo temp=stockInfoDAO.selectByCode(stockInfo.getCode());
			if(temp!=null&&temp.getId()>0){
				BeanUtils.copyProperties(stockInfo, temp);
			}
		}
		
	}
}
