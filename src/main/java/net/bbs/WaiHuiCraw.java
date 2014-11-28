package net.bbs;

import java.util.HashSet;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class WaiHuiCraw {
	public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";
	private static Set<Header> defaultHeaders;
	static {
		defaultHeaders = new HashSet<Header>();
	}
	
	private CloseableHttpClient closeableHttpClient;
	private BasicCookieStore cookieStore;

	public WaiHuiCraw() {
		cookieStore = new BasicCookieStore();
		closeableHttpClient = HttpClients.custom()
				.setDefaultHeaders(defaultHeaders).setUserAgent(UA)
				.setDefaultCookieStore(cookieStore).build();
	}
}
