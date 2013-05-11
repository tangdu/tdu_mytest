package net.tdu.httpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * post 请求
 * 
 * @author tangdu
 * 
 * @time 2013-3-27 上午11:39:49
 */
public class PostMethod {

	public static void main(String[] args) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://www.xx.login.do");
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("name", "username"));
		nvps.add(new BasicNameValuePair("pwd", "password"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			try {
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = httpResponse.getEntity();
					if (entity != null) {

					}
					EntityUtils.consume(entity);
				} else {
					System.out
							.println(httpResponse.getStatusLine().getStatusCode());
				}
			} finally{
				httpPost.releaseConnection();
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
