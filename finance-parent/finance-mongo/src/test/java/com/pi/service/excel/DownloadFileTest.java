package com.pi.service.excel;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.pi.service.download.DownloadFile;
import com.pi.service.download.DownloadRequest;

public class DownloadFileTest {

	public static void main(String[] args) throws InvalidFormatException, UnsupportedOperationException {
		String url = "http://market.finance.sina.com.cn/downxls.php?date=2016-08-31&symbol=sh601766";
		String date = "2016-08-31";
		String symbol = "sh601766";
		DownloadRequest request = new DownloadRequest();
		request.setUrl(url);
		Map<String, String> param = new HashMap<String, String>();
		param.put("date", date);
		param.put("symbol", symbol);
		request.setParam(param);
		DownloadFile.downloadGet(request);
	}

}
