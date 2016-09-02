package com.pi.service.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pi.stock.model.StockInfo;

public class DownloadFile {
	protected final transient static Logger logger = LoggerFactory.getLogger(DownloadFile.class);
	private final static String USER_AGENT = "Mozilla/5.0";

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
		logger.info("下载文件开始,url:[{}],请求参数:[{}]", info.getUrl(),info.getParam().toString());

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(info.getUrl());

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("Content-Type", "application/vnd.ms-excel;charset=gb2312");
		try {
			HttpResponse response = client.execute(request);
			logger.info("Response Code : [{}]" , response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "gb2312"));

			List<String> resultList = new ArrayList<String>();
			String line = "";
			while ((line = rd.readLine()) != null) {
				// 结果为一行一行的,同时通过\t分割
				resultList.add(line);
			}

		} catch (IOException e) {
		} finally {
		}
		logger.info("下载文件结束");

	}
}
