package com.pi.service.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pi.service.download.DownloadFile;

public abstract class AbstractResponseHandler implements ResponseHandler<List<String>> {

	protected final static Logger logger = LoggerFactory.getLogger(DownloadFile.class);

	public List<String> handleResponse(HttpResponse response) {
		logger.info("Response Code : [{}]", response.getStatusLine().getStatusCode());
		List<String> resultList = new ArrayList<String>();
		try {
			InputStream in = response.getEntity().getContent();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, "gb2312"));
			String line = "";
			while ((line = rd.readLine()) != null) {
				// 结果为一行一行的,同时通过\t分割
				resultList.add(line);
			}
			filter(resultList);
			in.close();
		} catch (UnsupportedOperationException | IOException e) {
			logger.error("", e);
		} finally {
			if (response != null) {
				try {
					((CloseableHttpResponse) response).close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
		}

		return resultList;
	}

	public abstract void filter(List<String> resultList);
}
