package net.tdu.httpClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class OSCLogin {
	public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";
	private static Set<Header> defaultHeaders;
	static {
		defaultHeaders = new HashSet<Header>();
		defaultHeaders
				.add(new BasicHeader(
						"Referer",
						"https://passport.csdn.net/account/loginbox?callback=logined&hidethird=1&from=http%3a%2f%2fwww.csdn.net%2f"));
	}
	private CloseableHttpClient closeableHttpClient;
	private BasicCookieStore cookieStore;

	public OSCLogin() {
		cookieStore = new BasicCookieStore();
		closeableHttpClient = HttpClients.custom()
				.setDefaultHeaders(defaultHeaders).setUserAgent(UA)
				.setDefaultCookieStore(cookieStore).build();
	}

	private static final String index_url = "http://my.oschina.net/tangdu";
	private static final String login_url = "https://www.oschina.net/action/user/hash_login";

	private String getShaPwd(String pwd) {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
		try {
			engine.eval(new InputStreamReader(OSCLogin.class.getClassLoader()
					.getResourceAsStream("sha1.js")));
			Object t = engine.eval("CryptoJS.SHA1('" + pwd + "').toString();");
			if (t != null) {
				return t.toString();
			}
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void main() {
		String email = "tangdu0228yes@163.com";
		String password = getShaPwd("^tangdu$0228_");
		if (password != null) {
			return;
		}

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("email", email));
		nvps.add(new BasicNameValuePair("pwd", password));

		HttpPost httpPost = new HttpPost(login_url);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		try {
			CloseableHttpResponse response = closeableHttpClient
					.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				getIndex();
				/*
				 * for (Cookie cookie : cookieStore.getCookies()) {
				 * System.out.println(cookie); }
				 */
				System.out.println("success");
			} else {
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
