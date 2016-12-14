package com.finance.component.client;

import java.io.IOException;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.finance.pojo.request.BaseRequest;

@Component
public class FinanceClient {

	@Autowired
	private HttpConnectionManager manager;
	@Autowired
	private CommClient commclient;

	private Logger logger = LoggerFactory.getLogger(FinanceClient.class);

	@Async
	public <T> void get(BaseRequest<T> request) {
		logger.info("下载文件开始,url:[{}],请求参数:[{}]", request.getUrl(), request.getParam().toString());

		CloseableHttpClient client = manager.getHttpClient();
		HttpRequestBase httpRequestBase = commclient.getRequestBase(request);
		try {
			long start = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			T object = (T) client.execute(httpRequestBase, request.getResponseHandler());

			request.getProcessor().process(request, object);
			logger.info("处理文件用时[{}] ms", (System.currentTimeMillis() - start));
		} catch (IOException e) {
			logger.error("获取文件失败,url:[{}],错误:[{}]", request.getUrl(), e);
		} finally {
			// if (client != null) {
			// try {
			// client.close();
			// } catch (IOException e) {
			// logger.error("关闭连接失败,错误:[{}]", e);
			// }
			// }
		}
		logger.info("下载文件结束");
	}
}
