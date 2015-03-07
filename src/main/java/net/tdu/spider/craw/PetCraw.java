package net.tdu.spider.craw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 抓取小测试
 * 
 * @author tangdu
 * @see ClientMultPetImgiThread.java
 * @see TaskThread.java
 * @time 2013-3-28 下午5:19:53
 */
public class PetCraw {
	static final String base_url = "http://www.sgou.com/";
	static final String base_save_path = "F://other//pets//";
	
	public synchronized void getDescInfo(String content, String folderName) throws Exception {
		Document doc=Jsoup.parse(content);
		String cssQuery = "div[id=cont1][class=common]";
		Elements elements = doc.select(cssQuery);
		if (elements != null) {
			FileUtils.write(new File(base_save_path + folderName + "//"
					+ folderName + ".txt"), elements.text(), "gbk");
		}
	}

	/*private void getImg(Document doc, String folderName) throws Exception {
		String cssQuery = "div[id=cont5] ul[class=pic_list] li a";
		Elements elements = doc.select(cssQuery);
		if (elements != null) {
			Element element = elements.get(0);// 取——0
			String img_url = base_url + element.attr("href");

			Document document = getDocument(img_url);
			// 取得内容
			// String content=document.select("div[id=digest]").text();
			// 下载图片
			Elements imgs = document.select("div[id=sImage] img");
			if (imgs != null) {
				for (Element img : imgs) {
					String imgUrl = base_url
							+ img.attr("src").replace("thumb_60_60_", "");
					download(imgUrl, folderName);
				}
			}
		}
	}*/

	private synchronized void download(String imgurl, String folderName) throws Exception {

		if (imgurl != null && !"".equals(imgurl)) {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(imgurl);
			HttpResponse response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String imgname = imgurl.substring(imgurl.lastIndexOf("/"));
					File file = new File(base_save_path + folderName + imgname);
					FileOutputStream fileOutputStream = FileUtils
							.openOutputStream(file);
					InputStream input = entity.getContent();
					try {
						IOUtils.copy(input, fileOutputStream);
					} finally {
						IOUtils.closeQuietly(fileOutputStream);
					}
					EntityUtils.consume(entity);
				}
			}
		}
	}
}
