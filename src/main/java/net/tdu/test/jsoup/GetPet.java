package net.tdu.test.jsoup;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class GetPet {
	public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";

	public static void main(String[] args) {

		try {
			long start = System.currentTimeMillis();
			String index_url = "http://www.sgou.com/pet/58/";
			String index_cssQuery = "div[id=right_1_content] div[style=display:none] ul li a";
			Document document = Jsoup
					.connect(index_url)
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.2 Safari/537.17")
					.header("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
					.header("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3")
					.header("Accept-Encoding", "gzip,deflate,sdch")
					.header("Accept-Language", "zh-CN,zh;q=0.8")
					.header("Connection", "keep-alive")
					.header("Host", "www.sgou.com").get();

			Elements elements = document.select(index_cssQuery);
			StringBuilder builder = new StringBuilder();
			for (Element e : elements) {
				builder.append(e.attr("href")).append("#").append(e.text())
						.append("\n");
			}
			if (builder.lastIndexOf("\n") > -1) {
				String content = builder
						.substring(0, builder.lastIndexOf("\n"));
				FileUtils
						.write(new File(
								"F:\\workspace\\jaeespace\\mytest\\src\\main\\resources\\pets-href.txt"),
								content);
			}
			long end = System.currentTimeMillis();
			System.out.println("times is : " + (end - start) / 1000 + " s");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getPetImgUrl() {
		
	}
}
