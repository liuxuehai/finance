package com.pi.service.excel;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.pi.service.download.DownloadFile;
import com.pi.service.mongo.StockInfoRepository;
import com.pi.stock.model.StockInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class JxlsReaderTest {

	// @Autowired
	JxlsReader jxlsReader;
	// @Autowired
	private MongoTemplate mongo;

	@Test
	public void test() {
		Map beans = new HashMap();
		List departments = new ArrayList();
		beans.put("stockInfos", departments);
		jxlsReader.readExcel("classpath:stock.xml", "F:\\tools\\上市公司列表.xlsx", beans);
		departments = (List) beans.get("stockInfos");
		System.out.println(departments.size());

		mongo.insert(departments, StockInfo.class);
	}

	@Test
	public void test2() {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is("000012"));
		StockInfo info = mongo.findOne(query, StockInfo.class);

		System.out.println(info);
	}

	@Test
	public void test3() {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is("900012"));
		StockInfo info = mongo.findOne(query, StockInfo.class);
		// info.setIndustry("ere");
		Update update = new Update().set("name", "22");
		mongo.upsert(query, update, StockInfo.class);
	}

	@Test
	public void test4() {
		Map beans = new HashMap();
		List departments = new ArrayList();
		beans.put("stockInfos", departments);
		jxlsReader.readExcel("classpath:stock2.xml", "F:\\数据\\test.xlsx", beans);
		departments = (List) beans.get("stockInfos");
		System.out.println(departments.size());

		// mongo.insert(departments, StockInfo.class);
	}

	@Test
	public void test6() {
		Map beans = new HashMap();
		List departments = new ArrayList();
		beans.put("stockInfos", departments);
		jxlsReader.readExcel("classpath:stock2.xml", "F:\\数据\\sh601766_成交明细_2016-08-24.xls", beans);
		departments = (List) beans.get("stockInfos");
		System.out.println(departments.size());

		// mongo.insert(departments, StockInfo.class);
	}

	@Test
	public void test7() {
		String path = "F:\\数据\\sh601919_成交明细_20160901.xls";
		try {

			Workbook workbook = WorkbookFactory.create(new BufferedInputStream(new FileInputStream(path)));
			Sheet sheet = workbook.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				// if(StringUtils.isEmpty(getSheetCellContent(row.getCell(0,
				// Row.RETURN_BLANK_AS_NULL)))){
				// continue;
				// }
				// StockInfo info=new StockInfo();
				// info.set

			}

		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test5() {
		Map beans = new HashMap();
		List departments = new ArrayList();
		beans.put("stockInfos", departments);
		jxlsReader.readExcel("classpath:stock5.xml", "F:\\tools\\sh601766_成交明细_2016-08-24.xls", beans);
		departments = (List) beans.get("stockInfos");
		System.out.println(departments.size());

		// mongo.insert(departments, StockInfo.class);
	}

	@Test
	public void test10() {
		String urlPath = "http://market.finance.sina.com.cn/downxls.php?date=2016-08-31&symbol=sh601766";
		URL url;
		try {
			url = new URL(urlPath);
			DownloadFile.downloadFile(url, "d:\\test\\", "ee.xlsx");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
