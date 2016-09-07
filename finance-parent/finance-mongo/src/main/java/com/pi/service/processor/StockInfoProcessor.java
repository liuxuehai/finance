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
import com.pi.stock.model.StockInfo;

@Component
public class StockInfoProcessor implements BaseProcessor<List<String>> {

	private Logger logger = LoggerFactory.getLogger(StockInfoConsumer.class);
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
			stock.setCodeA(temp[2].trim());
			stock.setNameA(temp[3].trim());
			stock.setDateA(temp[4].trim());
			stock.setgCapitalA(temp[5].trim());
			stock.setfCapitalA(temp[6].trim());
			stockInfos.add(stock);

			Query query = new Query();
			query.addCriteria(Criteria.where("code").is(temp[0].trim()));
			Update update = new Update();
			if (request.getParam().get("stockType") == "1") {
				update.set("name", temp[1].trim()).set("codeA", temp[2].trim()).set("nameA", temp[3].trim())
						.set("dateA", temp[4].trim()).set("gCapitalA", temp[5].trim()).set("fCapitalA", temp[6].trim());
			} else if (request.getParam().get("stockType") == "2") {
				update.set("name", temp[1].trim()).set("codeB", temp[2].trim()).set("nameB", temp[3].trim())
						.set("dateB", temp[4].trim()).set("gCapitalB", temp[5].trim()).set("fCapitalB", temp[6].trim());
			}

			StockInfo infoQ = mongo.findOne(query, StockInfo.class);
			if (infoQ == null) {
				mongo.insert(stock);
			} else {
				mongo.upsert(query, update, StockInfo.class);
			}
		}

	}

}
