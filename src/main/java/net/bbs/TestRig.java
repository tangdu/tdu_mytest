package net.bbs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

public class TestRig {
	public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";
	private static Set<Header> defaultHeaders;
	static {
		defaultHeaders = new HashSet<Header>();
	}
	
	private CloseableHttpClient closeableHttpClient;
	private BasicCookieStore cookieStore;

	public TestRig() {
		cookieStore = new BasicCookieStore();
		closeableHttpClient = HttpClients.custom()
				.setDefaultHeaders(defaultHeaders).setUserAgent(UA)
				.setDefaultCookieStore(cookieStore).build();
	}

	private static final String register_url = "http://192.168.18.7:9999/bbs/register.jspx";
	private static final String login_url = "http://192.168.18.7:9999/bbs/login.jspx";
	private static final String logout_url = "http://192.168.18.7:9999/bbs/bbslogout.jspx";


	@Test
	public void userAdd() {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "1UccLDY"));
		nvps.add(new BasicNameValuePair("password", "1UccLDY"));
		nvps.add(new BasicNameValuePair("captcha", "captcha"));
		nvps.add(new BasicNameValuePair("email", "1UccLDY@163.com"));
		nvps.add(new BasicNameValuePair("submit", "")); 	

		HttpPost httpPost = new HttpPost(login_url);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		try {
			httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
			CloseableHttpResponse response = closeableHttpClient
					.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("success");
			} else {
				System.out.println("error "+response.getStatusLine().getStatusCode());
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
	public void userEidt() {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "1UccLDY"));
		nvps.add(new BasicNameValuePair("password", "1UccLDY"));
		nvps.add(new BasicNameValuePair("captcha", "captcha"));
		nvps.add(new BasicNameValuePair("email", "1UccLDY@163.com"));
		nvps.add(new BasicNameValuePair("submit", "")); 	

		HttpPost httpPost = new HttpPost(login_url);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		try {
			httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
			CloseableHttpResponse response = closeableHttpClient
					.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("success");
			} else {
				System.out.println("error "+response.getStatusLine().getStatusCode());
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
	public void login(){
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "1UccLDY"));
		nvps.add(new BasicNameValuePair("password", "1UccLDY"));
		nvps.add(new BasicNameValuePair("captcha", "captcha"));
		nvps.add(new BasicNameValuePair("cookieType", "4"));//不保存COOKIE
		nvps.add(new BasicNameValuePair("processUrl", "http://192.168.18.7:9999/bbs/bbslogin.jspx")); 	
		nvps.add(new BasicNameValuePair("returnUrl", "")); 	
		nvps.add(new BasicNameValuePair("submit", "")); 	

		HttpPost httpPost = new HttpPost(login_url);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		try {
			httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
			CloseableHttpResponse response = closeableHttpClient
					.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("success");
			} else {
				System.out.println("error "+response.getStatusLine().getStatusCode());
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
	public void logout(){
		
	}
}
