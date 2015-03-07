package net.tdu.httpClient.thread.t02;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ImgDownThread extends Thread {

	
	private CloseableHttpClient closeableHttpClient;
	private HttpGet httpGet;
	private String name;
	private HttpContext httpContext;
	
	
	public ImgDownThread(CloseableHttpClient closeableHttpClient,
			HttpGet httpGet, String name) {
		super();
		this.closeableHttpClient = closeableHttpClient;
		this.httpGet = httpGet;
		this.name = name;
		this.httpContext = new BasicHttpContext();
	}


	@Override
	public void run() {
		try {
			CloseableHttpResponse httpResponse=closeableHttpClient.execute(httpGet,httpContext);
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity=httpResponse.getEntity();
				if(entity!=null){
					Document document=Jsoup.parse(entity.getContent(), "UTF-8","");
					
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			httpGet.releaseConnection();
		}
		
	}

}
