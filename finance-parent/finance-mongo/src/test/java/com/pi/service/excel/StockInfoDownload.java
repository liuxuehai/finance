package com.pi.service.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.pi.common.Constants;
import com.pi.service.download.DownloadFile;
import com.pi.service.download.DownloadRequest;
import com.pi.service.handler.SinaDetailHandler;

public class StockInfoDownload {

	public static void main(String[] args) {
		String url = "http://query.sse.com.cn/security/stock/downloadStockListFile.do?stockType=1";
		DownloadRequest request = new DownloadRequest();
		request.setUrl(url);
		Map<String, String> param = new HashMap<String, String>();
		SinaDetailHandler sinaDetailHandle=new SinaDetailHandler();

		Header h1=new BasicHeader("User-Agent", Constants.USER_AGENT);
		Header h2=new BasicHeader("Accept-Encoding", Constants.Accept_Encoding);
		Header h3=new BasicHeader("Referer", Constants.Referer_SHANGHAI);
		Header h4=new BasicHeader("Accept-Language", Constants.Accept_Language);
		Header h5=new BasicHeader("Content-Type", Constants.Content_Excel);
		Header h6=new BasicHeader("Accept", Constants.Accept);
		
		List<Header> headers=new ArrayList<Header>();
		headers.add(h6);
		headers.add(h5);
		headers.add(h4);
		headers.add(h3);
		headers.add(h2);
		headers.add(h1);
		request.setHeaders(headers);
		
		request.setResponseHandler(sinaDetailHandle);
		request.setParam(param);
		DownloadFile.downloadGet(request);
	}
}
