package com.pi.service.header;

import java.util.HashMap;
import java.util.Map;

import com.pi.common.Constants;

public class HeaderBuilder {

	public final static String USER_AGENT = "User-Agent";
	public final static String ACCEPT_ENCODING = "Accept-Encoding";
	public final static String CONTENT_TYPE = "Content-Type";
	public final static String ACCEPT_LANGUAGE = "Accept-Language";
	public final static String REFERER = "Referer";
	public final static String ACCEPT = "Accept";
	public final static String SHANGHAI = "shanghai";

	public final static String SINA = "sina";

	public static Map<String, Map<String, String>> cache = new HashMap<String, Map<String, String>>();

	static {
		// 上海证券交易所header
		Map<String, String> header = new HashMap<String, String>();
		header.put("User-Agent", Constants.USER_AGENT);
		header.put("Accept-Encoding", Constants.Accept_Encoding);
		header.put("Referer", Constants.Referer_SHANGHAI);
		header.put("Accept-Language", Constants.Accept_Language);
		header.put("Content-Type", Constants.Content_Excel);
		header.put("Accept", Constants.Accept);
		cache.put(SHANGHAI, header);

		// sina header
		Map<String, String> header2 = new HashMap<String, String>();
		header2.put("User-Agent", Constants.USER_AGENT);
		header2.put("Accept-Encoding", Constants.Accept_Encoding);
		header2.put("Referer", Constants.Referer_SHANGHAI);
		header2.put("Accept-Language", Constants.Accept_Language);
		header2.put("Content-Type", Constants.Content_Excel);
		header2.put("Accept", Constants.Accept);
		cache.put(SINA, header2);
	}

	public static Map<String, String> getHeader(String type) {
		return cache.get(type);
	}

}
