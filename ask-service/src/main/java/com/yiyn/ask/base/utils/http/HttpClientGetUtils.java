package com.yiyn.ask.base.utils.http;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientGetUtils {
	
	public static CloseableHttpClient initHttpClient() {
		return HttpClientBuilder.create().build();
	}
	
	/**
	 * Send and get response from the specified url
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static HttpResponse getHttpGetResponse(String url) throws Exception{
		HttpGet hg = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(HttpClientConstants.socketTimeout)
				.setConnectTimeout(HttpClientConstants.connectTimeout).build();// 设置请求和传输超时时�?
		hg.setConfig(requestConfig);
		hg.setHeader(HttpClientConstants.headerName_content_type, HttpClientConstants.headerValue_form);

		HttpResponse response = initHttpClient().execute(hg);
		return response;
	}
	
	/**
	 * Send and get response from the specified url, and convert to a string characters
	 * 
	 * @param url
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String getHttpGetContent(String url, String encoding) throws Exception{
		HttpResponse response = getHttpGetResponse(url);
		return IOUtils.toString(response.getEntity().getContent(), encoding);
	}
	
	public static void sendHttpGet(String url, Map<String,String> params) throws Exception{
		String queryString = generateQueryString(params);
		String wholeUrl = url + "?" + queryString;
		
		sendHttpGet(wholeUrl);
	}
	
	public static String generateQueryString(Map<String,String> params) throws Exception {
		StringBuffer sb = new StringBuffer();
		Iterator<String> keyIte = params.keySet().iterator();
		while(keyIte.hasNext()){
			String keyName = keyIte.next();
			if(sb.length()>0){
				sb.append("&");
			}
			sb.append(keyName).append("=").append(URLEncoder.encode(params.get(keyName),"UTF-8"));
		}
		return sb.toString();
	}
	
	public static void sendHttpGet(String url) throws Exception{
		CloseableHttpClient httpClient = initHttpClient();
		HttpGet hg = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
		hg.setConfig(requestConfig);
		hg.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		httpClient.execute(hg);
	}
	
}
