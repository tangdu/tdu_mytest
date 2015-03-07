package net.tdu.httpClient.easy;

import java.io.File;
import java.io.IOException;

import net.tdu.Constants;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class QQ {

	public static void main(String[] args) {
		try {
			Document document = Jsoup
					.connect(
							"http://user.qzone.qq.com/137338731/infocenter#!app=4&via=QZ.HashRefresh")
					.userAgent(Constants.USER_AGENT).get();
			FileUtils.write(new File("1.txt"), document.html());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
