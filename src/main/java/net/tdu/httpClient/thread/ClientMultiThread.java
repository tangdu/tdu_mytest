package net.tdu.httpClient.thread;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.tdu.Constants;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * 多线程
 * 
 * @author tangdu
 * 
 * @time 2013-3-27 下午12:23:40
 */
public class ClientMultiThread {

	public static void main(String[] args) {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);

		CloseableHttpClient httpclient = HttpClients.custom()
				.setConnectionManager(cm).build();
		try {
			long start = System.currentTimeMillis();
			List<String> urls = FileUtils.readLines(new File(
					Constants.RESOURCES_PATH + "pets-href.txt"));

			TaskThread taskThreads[] = new TaskThread[urls.size()];
			for (int i = 0; i < urls.size(); i++) {
				String lines[]=urls.get(i).split("#");
				HttpGet httpget = new HttpGet(lines[0]);
				taskThreads[i] = new TaskThread(httpclient, httpget, lines[1]);
			}

			for (int i = 0; i < taskThreads.length; i++) {
				taskThreads[i].start();
			}
			long end = System.currentTimeMillis();
			System.out.println("times is : " + (end - start) + " ms");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}
}
