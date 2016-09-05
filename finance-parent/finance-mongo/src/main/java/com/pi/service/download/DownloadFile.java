package com.pi.service.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadFile {
	
	protected final transient static Logger logger = LoggerFactory.getLogger(DownloadFile.class);
	private final static String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.82 Safari/537.36";

	public static void downloadFile(URL theURL, String filePath, String filename) throws IOException {
		File dirFile = new File(filePath);
		if (!dirFile.exists()) {
			// 文件路径不存在时，自动创建目录
			dirFile.mkdir();
		}
		// 从服务器上获取图片并保存
		URLConnection connection = theURL.openConnection();
		InputStream in = connection.getInputStream();
		FileOutputStream os = new FileOutputStream(filePath + filename);
		byte[] buffer = new byte[4 * 1024];
		int read;
		while ((read = in.read(buffer)) > 0) {
			os.write(buffer, 0, read);
		}
		os.close();
		in.close();
	}

	public static void downloadGet(DownloadRequest info) {
		logger.info("下载文件开始,url:[{}],请求参数:[{}]", info.getUrl(), info.getParam().toString());

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(info.getUrl());

		// add request header
		if (info.getHeaders() != null) {
			for (Header header : info.getHeaders()) {
				request.addHeader(header);
			}
		}

		try {
			List<String> list = client.execute(request, info.getResponseHandler());
			logger.info("总条数:[{}]", list.size());

		} catch (IOException e) {
		} finally {
		}
		logger.info("下载文件结束");

	}
}
