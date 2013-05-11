package net.tdu.spider.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * 解析超类
 * @author tangdu
 *
 * @time 2013-3-26 下午1:19:34
 */
public abstract class Parse {

	public Document parseDocument(String htmlSrc) {
		if (htmlSrc != null && !"".equals(htmlSrc)) {
			return Jsoup.parse(htmlSrc);
		}
		return null;
	}

	public Elements select(String htmlSrc, String cssQuery) {

		Document doc = parseDocument(htmlSrc);

		if (doc != null) {
			return doc.select(cssQuery);
		} else {
			return null;
		}
	}
	
	private static final String _ENCODING = "gb2312";
	
	/**
	 * 流转字符串
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public String getContent(InputStream inputStream) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				inputStream, _ENCODING));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
}
