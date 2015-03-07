package net.tdu.httpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

/**
 * 同一个CloseableHttpClient cookie 默认自动传送
 * 若是不同的请求，需要对cookieStore进行维护.例如cookie信息以字符串形式保存在数据库中。或是内存当中。
 * 一般地，建议使用保存在内在中，并使用上缓存；进行维护。
 */

public class LoginForm {
	public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";
	private static Set<Header> defaultHeaders;
	static {
		defaultHeaders = new HashSet<Header>();
		defaultHeaders.add(new BasicHeader("X-Requested-With", "XMLHttpRequest"));
		defaultHeaders.add(new BasicHeader("Content-type", "application/x-www-form-urlencoded"));
		defaultHeaders.add(new BasicHeader("Accept", "*/*"));
		defaultHeaders.add(new BasicHeader("Referer", "https://passport.csdn.net/account/loginbox?callback=logined&hidethird=1&from=http%3a%2f%2fwww.csdn.net%2f"));
	}

	private CloseableHttpClient closeableHttpClient;
	private BasicCookieStore cookieStore;
	
	private LoginForm() {
		cookieStore = new BasicCookieStore();
		closeableHttpClient = HttpClients.custom()
				.setDefaultHeaders(defaultHeaders).setUserAgent(UA)
				.setDefaultCookieStore(cookieStore).build();
	}
	
	public static void main(String[] args) {
		LoginForm form=new LoginForm();
		form.login("tangduDream", "");
		form.search("http://my.csdn.net/");
		form.closeConnection();
	}

	private void login(String name, String pwd) {
		String url = getLoginUrl(name, pwd);
		if(url!=null){
			HttpGet httpGet = new HttpGet(url);
			try {
				CloseableHttpResponse httpResponse=closeableHttpClient.execute(httpGet);
				
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = httpResponse.getEntity();
					if (entity != null) {
						printCookie(cookieStore.getCookies());
					}
					EntityUtils.consume(entity);
				}else{
					System.out.println(httpResponse.getStatusLine().getStatusCode());
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				httpGet.releaseConnection();//释放
			}
		}
	}

	private void search(String url) {
		if(url!=null && !"".equals(url)){
			HttpGet httpGet = new HttpGet(url);
			try {
				CloseableHttpResponse httpResponse=closeableHttpClient.execute(httpGet);
				
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = httpResponse.getEntity();
					if (entity != null) {
						String contentType = entity.getContentType().getValue();
						String encoding = "utf-8";
						if (contentType != null) {
							if (contentType.indexOf("=") > -1) {
								encoding = contentType.substring(contentType
										.indexOf("=") + 1);
							}
						}
						String context = EntityUtils.toString(entity, encoding);
						System.out.println(context.substring(0,3000));
					}
					EntityUtils.consume(entity);
				}else{
					System.out.println(httpResponse.getStatusLine().getStatusCode());
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				httpGet.releaseConnection();//释放
			}
		}
	}
	
	/**
	 * 关闭连接
	 */
	private void closeConnection(){
		if(closeableHttpClient!=null){
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void printCookie(List<Cookie> cookies) {
		if (cookies != null) {
			if (cookies.isEmpty()) {
				System.out.println("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("- " + cookies.get(i).toString());
				}
			}
		}
	}

	private String getLoginUrl(String name, String pwd) {
		StringBuilder sb = new StringBuilder(
				"https://passport.csdn.net/ajax/accounthandler.ashx?");
		sb.append("t=log");
		sb.append("&");
		sb.append("u={username}");
		sb.append("&");
		sb.append("p={password}");
		sb.append("&");
		sb.append("remember=0");
		sb.append("&");
		sb.append("f=http://www.csdn.net/");
		sb.append("&");
		sb.append("rand=" + Math.random());

		String url = sb.toString();
		try {
			name = URLEncoder.encode(name, "utf-8");
			pwd = URLEncoder.encode(pwd, "utf-8");
			url = url.replace("{username}", name).replace("{password}", pwd);
			return url;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String encodeParam(String param){
		try {
			return URLEncoder.encode(param,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
