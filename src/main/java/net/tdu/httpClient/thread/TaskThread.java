package net.tdu.httpClient.thread;

import net.tdu.spider.craw.PetCraw;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author tangdu
 * 
 * @time 2013-3-27 下午1:07:41
 */
public class TaskThread extends Thread {
	
	private static final Logger logger=LoggerFactory.getLogger(TaskThread.class);

	private CloseableHttpClient client;
	private HttpGet httpGet;
	private String text;
	private HttpContext httpContext;
	private PetCraw petCraw;

	public TaskThread(CloseableHttpClient client, HttpGet httpGet, String text) {
		super();
		this.client = client;
		this.httpGet = httpGet;
		this.text = text;
		this.httpContext = new BasicHttpContext();
		this.petCraw = new PetCraw();
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
						this.petCraw.getDescInfo(content, text);//保存文本信息
						EntityUtils.consume(entity);
					}
				} else {
					System.out.println(httpResponse.getStatusLine()
							.getStatusCode());
				}
			} catch (Exception e) {
				logger.error("url {} craw error {}",text,e);
				e.printStackTrace();
			} finally {
				httpResponse.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			httpGet.releaseConnection();
		}
	}

}
