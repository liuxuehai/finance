package com.pi.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pi.stock.dao.StockPriceDetailDAO;
import com.pi.stock.model.StockPriceDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class StockPriceDetailDAOTest {

	@Autowired
	StockPriceDetailDAO stockPriceDetailDAO;

	@Test
	public void test() {
		List<StockPriceDetail> stockPriceDetails = new ArrayList<StockPriceDetail>();

		for (int i = 0; i < 100; i++) {
			StockPriceDetail detail = new StockPriceDetail();
			detail.setCode("22");
			stockPriceDetails.add(detail);
		}

		stockPriceDetailDAO.insertPriceBatch(stockPriceDetails,"Price000001");

		int size = stockPriceDetails.size();
		int limit = 200;
		int start = 0;
		int end = start + limit;

		if (end > size) {

		} else {
			int part = size / limit;
			System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");

			for (int i = 0; i < part; i++) {
				List<StockPriceDetail> listPage = stockPriceDetails.subList(0, limit);
				System.out.println(listPage.size());
				stockPriceDetails.subList(0, limit).clear();
			}

			if (!stockPriceDetails.isEmpty()) {
				System.out.println(stockPriceDetails.size());
			}
		}
	}
}
