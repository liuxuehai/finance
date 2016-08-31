package com.pi.service.excel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class JxlsReaderTest {

	@Autowired
	JxlsReader jxlsReader;

	@Test
	public void test() {
		Map beans = new HashMap();
		List departments = new ArrayList();
		beans.put("stockInfos", departments);
		jxlsReader.readExcel(
				"classpath:stock.xml",
				"F:\\tools\\上市公司列表.xlsx", beans);
		departments = (List) beans.get("stockInfos");
		System.out.println(departments.size());
	}

}
