package net.tdu.httpClient.thread.t01;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author tangdu
 * 
 * @time 2013-3-27 下午1:07:41
 */
public class PetImgThread extends Thread {
	static final String base_url = "http://www.sgou.com/";
	private static final Logger logger = LoggerFactory
			.getLogger(PetImgThread.class);

	private CloseableHttpClient client;
	private HttpGet httpGet;
	private String text;
	private HttpContext httpContext;

	public PetImgThread(CloseableHttpClient client, HttpGet httpGet, String text) {
		super();
		this.client = client;
		this.httpGet = httpGet;
		this.text = text;
		this.httpContext = new BasicHttpContext();
	}

	@Override
	public void run() {
		try {
			System.out.println("excute " + text + " url " + httpGet.getURI());
			CloseableHttpResponse httpResponse = client.execute(httpGet,
					httpContext);
			try {
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = httpResponse.getEntity();
					if (entity != null) {
						String content = EntityUtils.toString(entity, "gbk");
						Document doc = Jsoup.parse(content);
						String cssQuery = "div[id=cont5] ul[class=pic_list] li a";
						Elements elements = doc.select(cssQuery);
						if (!elements.isEmpty()) {
							Element element = elements.get(0);// 取——0
							String img_url = base_url + element.attr("href");
							writeImgUrl(img_url);
						}
					}
				} else {
					System.out.println(httpResponse.getStatusLine()
							.getStatusCode());
				}
			} catch (Exception e) {
				logger.error("url {} craw error {}", text, e);
				e.printStackTrace();
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpGet.releaseConnection();
		}
	}

	private synchronized void writeImgUrl(String url) {
		try {
			FileUtils
					.write(new File(
							"F:\\workspace\\jaeespace\\mytest\\src\\main\\resources\\pets-img-href.txt"),
							url + "," + text+"\n",true);
		} catch (IOException e) {
			logger.error("write error", e);
			e.printStackTrace();
		}
	}

}
