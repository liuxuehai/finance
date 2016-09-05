package com.pi.service.client;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pi.base.ExcelRequest;
import com.pi.connection.HttpConnectionManager;

@Component
public class ExcelClient {
	
	@Autowired
	private HttpConnectionManager manager;
	@Autowired
	private PiClient piClient;
	
	private Logger logger = LoggerFactory.getLogger(ExcelClientTest.class);
	
	public void get(ExcelRequest request) {
		logger.info("下载文件开始,url:[{}],请求参数:[{}]", request.getUrl(), request.getParam().toString());

		CloseableHttpClient client = manager.getHttpClient();
		HttpRequestBase httpRequestBase = piClient.getRequestBase(request);
		try {
			List<String> list = client.execute(httpRequestBase, request.getResponseHandler());
			logger.info("总条数:[{}]", list.size());
		} catch (IOException e) {
			logger.error("获取文件失败,url:[{}],错误:[{}]", request.getUrl(), e);
		} finally {
//			if (client != null) {
//				try {
//					client.close();
//				} catch (IOException e) {
//					logger.error("关闭连接失败,错误:[{}]", e);
//				}
//			}
		}
		logger.info("下载文件结束");
	}
}
