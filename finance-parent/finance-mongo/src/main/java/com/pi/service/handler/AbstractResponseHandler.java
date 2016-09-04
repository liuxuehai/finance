package com.pi.service.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pi.service.download.DownloadFile;

public abstract class AbstractResponseHandler implements ResponseHandler<List<String>> {
	
	protected final static Logger logger = LoggerFactory.getLogger(DownloadFile.class);

	public List<String> handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		logger.info("Response Code : [{}]", response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "gb2312"));

		List<String> resultList = new ArrayList<String>();
		String line = "";
		while ((line = rd.readLine()) != null) {
			// 结果为一行一行的,同时通过\t分割
			resultList.add(line);
		}
		
		filter(resultList);
		
		return resultList;
	}

	public abstract void filter(List<String> resultList) ;
}
