package net.xscraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {
	public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";
	public static final String ENCODING="gbk";
	
	private HttpClientUtils(){}
	public static CloseableHttpClient getDefaultClient(){
		List<Header> defaultHeaders=new ArrayList<Header>();
		BasicHeader header1=new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		BasicHeader header2=new BasicHeader("Accept-Encoding", "gzip,deflate,sdch");
		BasicHeader header3=new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
		BasicHeader header4=new BasicHeader("Connection", "keep-alive");
		BasicHeader header5=new BasicHeader("Host", "www.jdxs.net");
		BasicHeader header6=new BasicHeader("Cookie", "CNZZDATA1583454=cnzz_eid%3D764537937-1423570834-%26ntime%3D1423576234");
		defaultHeaders.add(header1);defaultHeaders.add(header2);defaultHeaders.add(header3);defaultHeaders.add(header4);
		defaultHeaders.add(header5);defaultHeaders.add(header6);
		//PoolingHttpClientConnectionManager  connManager=new PoolingHttpClientConnectionManager(); 
		CloseableHttpClient client = HttpClients.custom()
				//.setConnectionManager(connManager)
				.setDefaultHeaders(defaultHeaders)
				.setUserAgent(UA).build();
		return client;
	}
	
	public static CloseableHttpClient getDefaultClient(HttpHost proxy){
		CloseableHttpClient client = HttpClients.custom()
				.setProxy(proxy)
				.setUserAgent(UA).build();
		return client;
	}
	
	public static String getDataStr(CloseableHttpClient client,String url){
		String body=null;
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response=null;
		try {
			response=client.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					body= EntityUtils.toString(entity, ENCODING);
					EntityUtils.consume(entity);
				}
			}else{
				System.out.println("ERROR");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			httpGet.abort();
			httpGet.releaseConnection();
			try {
				if(client!=null)
					client.close();
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
	
	public static String getDataStr(String url){
		return getDataStr(getDefaultClient(),url);
		//return getDataStr(getDefaultClient(new HttpHost("110.4.24.178", 80)),url);
	}
	
	//TODO 
	public String postDataStr(HttpClient client,String url){
		return null;
	}
}
