package com.pi.service.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.pi.stock.model.StockInfo;

@Service
public interface StockInfoRepository extends MongoRepository<StockInfo, String> {

	public StockInfo findByCode(String code);

}
