package net.tdu.httpClient.thread.t02;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.tdu.Constants;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class PetImgDownLoad {

	public static void main(String[] args) {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);
		
		CloseableHttpClient httpclient = HttpClients.custom()
				.setConnectionManager(cm).build();
		try {
			List<String> urls = FileUtils.readLines(new File(
					Constants.RESOURCES_PATH + "pets-img-href.txt"));
			
			ImgDownThread taskThreads[] = new ImgDownThread[urls.size()];
			for (int i = 0; i < urls.size(); i++) {
				if("".equals(urls.get(i))){
					continue;
				}
				String lines[]=urls.get(i).split("#");
				HttpGet httpget = new HttpGet(lines[0]);
				taskThreads[i] = new ImgDownThread(httpclient, httpget, lines[1]);
			}

			for (int i = 0; i < taskThreads.length; i++) {
				taskThreads[i].start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
