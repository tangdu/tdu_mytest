package net.tdu.httpClient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * 基本例子
 * @author tangdu
 *
 * @time 2013-3-27 上午10:47:34
 */
public class HttpClientBase {
	public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";

	@Test
	public void crawBd() {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.baidu.com");
		httpGet.addHeader("User-Agent", UA);
		try {
			CloseableHttpResponse response = client.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
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
					System.out.println(context);
					EntityUtils.consume(entity);
				}
			} else if (isRedirect(response.getStatusLine().getStatusCode())) {
				System.out.println("地址重定向....");
			} else {
				System.out.println(response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpGet.releaseConnection();//
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isRedirect(int statusCode) {
		if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
				|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY
				|| statusCode == HttpStatus.SC_SEE_OTHER
				|| statusCode == HttpStatus.SC_TEMPORARY_REDIRECT) {
			return true;
		}
		return false;
	}
}
