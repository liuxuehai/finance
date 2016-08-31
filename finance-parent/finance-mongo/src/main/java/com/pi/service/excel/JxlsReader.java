package com.pi.service.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

@Service
public class JxlsReader {

	protected final transient Logger logger = LoggerFactory.getLogger(getClass());

	public void readExcel(String xmlConfig, String dataXLS, Map beans) {
		logger.info("解析excel开始,配置文件地址:[{}],数据文件地址:[{}]", xmlConfig, dataXLS);
		try {
			File input = ResourceUtils.getFile(xmlConfig);
			File inputData = ResourceUtils.getFile(dataXLS);
			InputStream inputXML = new BufferedInputStream(new FileInputStream(input));
			XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
			InputStream inputXLS = new BufferedInputStream(new FileInputStream(inputData));
			XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
			logger.info("解析结果[{}]", readStatus.isStatusOK());
		} catch (IOException | SAXException e) {
			logger.info("解析出错[{}]", e);
		} catch (InvalidFormatException e) {
			logger.info("解析出错[{}]", e);
		}
	}

}
