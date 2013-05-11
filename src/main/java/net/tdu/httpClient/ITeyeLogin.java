package net.tdu.httpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * ITeye denglu
 * @author tangdu
 *
 * @time 2013-4-2 下午9:26:44
 */
public class ITeyeLogin {
	public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";
	private static Set<Header> defaultHeaders;
	static {
		defaultHeaders = new HashSet<Header>();
	}
	
	private CloseableHttpClient closeableHttpClient;
	private BasicCookieStore cookieStore;

	public ITeyeLogin() {
		cookieStore = new BasicCookieStore();
		closeableHttpClient = HttpClients.custom()
				.setDefaultHeaders(defaultHeaders).setUserAgent(UA)
				.setDefaultCookieStore(cookieStore).build();
	}

	private static final String index_url = "http://www.iteye.com/login";
	private static final String login_url = "http://tangdudream.iteye.com/";


	@Test
	public void main() {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("authenticity_token", "3B89Y4UZbHolEz9FnpfelBbATbRdrcAwCRP+1UccLDY+"));
		nvps.add(new BasicNameValuePair("name", "tangduDream"));
		nvps.add(new BasicNameValuePair("password", ""));
		nvps.add(new BasicNameValuePair("button", "登　录")); 	

		HttpPost httpPost = new HttpPost(login_url);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		try {
			//httpPost.addHeader("Cookie","_javaeye_cookie_id_=1364687305517901; remember_me=no; __utma=191637234.1248082986.1364687308.1364907999.1364908430.18; __utmb=191637234.10.10.1364908430; __utmc=191637234; __utmz=191637234.1364908430.18.16.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=pdfbox%20%E8%AF%BB%E5%8F%96%E4%B9%B1%E7%A0%81; _javaeye3_session_=BAh7CDoRb3JpZ2luYWxfdXJpIhpodHRwOi8vd3d3Lml0ZXllLmNvbS86D3Nlc3Npb25faWQiJTA1YWZkNzFkNzI3M2Y1MGY0OWQ4MmY4MGNjZWI0YmZmOhBfY3NyZl90b2tlbiIxNG1JOUgvTDRaMWpBQjR1aW5YT0lKZzYwMno0VDZ2ZGZoaDdKS3FtbEQ0bz0%3D--ee47df9c36309e0c4313fa93eaa6b41c47f4b9d7");
			httpPost.addHeader("Host","www.iteye.com");
			httpPost.addHeader("Origin","http://www.iteye.com");
			httpPost.addHeader("Referer","http://www.iteye.com/login");
			httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
			
			CloseableHttpResponse response = closeableHttpClient
					.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("success");
			} else {
				for(Cookie cookie :cookieStore.getCookies()){
					System.out.println(cookie);
				}
				for(Header header :response.getAllHeaders()){
					System.out.println(header.getName()+"----"+header.getValue());
				}
				getIndex();
				System.out.println("error");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Test
	public void testPwd() {

	}

	public void getIndex() {
		if (closeableHttpClient == null) {
			return;
		}

		HttpGet httpGet = new HttpGet(index_url);
		try {
			CloseableHttpResponse response = closeableHttpClient
					.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String cnt = EntityUtils.toString(entity);
					System.out.println(cnt.substring(0, 3000));
				}

				EntityUtils.consume(entity);
			} else {
				System.out.println("error");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpGet.releaseConnection();
		}
	}
}
