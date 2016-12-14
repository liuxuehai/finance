package com.finance.pojo.handler;

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


public abstract class AbstractResponseHandler implements ResponseHandler<List<String>> {

	protected final static Logger logger = LoggerFactory.getLogger(AbstractResponseHandler.class);
	//是否移除excel头
	private boolean removeTitle=false;

	public List<String> handleResponse(HttpResponse response) {
		int status=response.getStatusLine().getStatusCode();
		logger.info("Response Code : [{}]", status);
		
		List<String> resultList = new ArrayList<String>();
        if(status!=200){
			return resultList;
		}
		try {
			InputStream in = response.getEntity().getContent();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, "gb2312"));
			String line = "";
			while ((line = rd.readLine()) != null) {
				// 结果为一行一行的,同时通过\t分割
				resultList.add(line);
			}
			
			if(removeTitle){
				resultList.remove(0);
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

	public boolean isRemoveTitle() {
		return removeTitle;
	}

	public void setRemoveTitle(boolean removeTitle) {
		this.removeTitle = removeTitle;
	}

	public abstract void filter(List<String> resultList);
}
