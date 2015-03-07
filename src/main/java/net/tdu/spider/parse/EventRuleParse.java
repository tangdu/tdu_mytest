package net.tdu.spider.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.junit.Test;

/**
 * 条例解析
 * 
 * @author tangdu
 * 
 * @time 2013-3-26 下午1:20:04
 */
public class EventRuleParse extends Parse {

	private static final String _FILE = "trs_tmp24_31386.htm";
	private static final String _ENCODING = "gb2312";

	public void parse(String htmlSrc) {
		/**
		 * 内容展示文本标签没有层次结构,按子父节点来说。条例都直属于body; 所以解析靠正则完成。
		 */
		Document doc = parseDocument(htmlSrc);

		String html = doc.html();// 得到所有html源码
		String chapters[] = html.split("<h3>第.*?章 &nbsp;.*?</h3>");// 得到按第_章分隔
		System.out.println("共：" + chapters.length + "章");

		int cnt = 0;// 条例
		if (chapters != null) {
			for (int i = 0; i < chapters.length; i++) {
				if (i == 0) {
					System.out.println("标题：" + getTitle(chapters[i]) + "\n");// 为标题内容
				} else {
					System.out.println("第" + i + "章");// 正文部分
					String list[] = chapters[i]
							.split("&nbsp;&nbsp;&nbsp;&nbsp;第.*?条 &nbsp;");
					for (int j = 0; j < list.length; j++) {
						if (j > 0) {
							cnt++;
							String tmp = list[j].replaceAll("<[^>]*>", "")// 去掉所有标记
									.replaceAll("\n|\\s", "")// 去掉所有回车,空格
									.replaceAll("&nbsp;", "");// 去掉&nbsp;
							System.out.println("第 " + cnt + "条：     " + tmp);
						}
					}
				}
			}
		}
	}

	/**
	 * get title
	 * 
	 * @param htmlSrc
	 * @return
	 */
	public String getTitle(String htmlSrc) {
		if (htmlSrc != null && !"".equals(htmlSrc)) {
			Pattern pattern = Pattern.compile("\\（.*?\\）");
			Matcher matcher = pattern.matcher(htmlSrc);

			if (matcher.find()) {
				return matcher.group();
			}
		}
		return null;
	}

	/**
	 * 最后一个条例特殊处理
	 * 
	 * @param htmlSrc
	 * @return
	 */
	public String getLastRule(String htmlSrc) {
		if (htmlSrc != null && !"".equals(htmlSrc)) {
			int len = htmlSrc
					.indexOf("&nbsp;&nbsp;&nbsp;&nbsp;*** &nbsp;&nbsp;");
			if (len > -1) {
				return htmlSrc.substring(len);
			}
		}
		return null;
	}

	@Test
	public void run() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(_FILE);
		try {
			String htmlSrc = super.getContent(inputStream);
			parse(htmlSrc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getInfo() {
		// 得到基本信息-用正则实现
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(_FILE);
		Pattern pattern0 = Pattern
				.compile("var str0=\"<font color=red>【文　　号】</font>(.*?)<BR>\"");
		Pattern pattern2 = Pattern
				.compile("var str2=\"<font color=red>【颁布单位】</font>(.*?)<BR>\"");
		Pattern pattern3 = Pattern
				.compile("var str3=\"<font color=red>【颁布日期】</font>(.*?)<BR>\"");
		Pattern pattern4 = Pattern
				.compile("var str4=\"<font color=red>【实施日期】</font>(.*?)<BR>\"");
		Pattern pattern5 = Pattern
				.compile("var str6=\"<font color=red>【分 类 号】</font>(.*?)<BR>\"");
		String htmlSrc;
		// 得到制度文件下载地址
		Pattern pattern6 = Pattern.compile("var curchannel=\"(.*?)\";");
		Pattern pattern7 = Pattern.compile("var url_gotodownload=\"(.*?)\";");
		Pattern pattern8 = Pattern.compile("var currecord=\"(.*?)\";");
		try {
			htmlSrc = getContent(inputStream);
			Matcher matcher0 = pattern0.matcher(htmlSrc);
			Matcher matcher2 = pattern2.matcher(htmlSrc);
			Matcher matcher3 = pattern3.matcher(htmlSrc);
			Matcher matcher4 = pattern4.matcher(htmlSrc);
			Matcher matcher5 = pattern5.matcher(htmlSrc);
			Matcher matcher6 = pattern6.matcher(htmlSrc);
			Matcher matcher7 = pattern7.matcher(htmlSrc);
			Matcher matcher8 = pattern8.matcher(htmlSrc);

			StringBuilder builder = new StringBuilder();
			if (matcher0.find()) {
				System.out.println(matcher0.group(1));
			}
			if (matcher2.find()) {
				System.out.println(matcher2.group(1));
			}
			if (matcher3.find()) {
				System.out.println(matcher3.group(1));
			}
			if (matcher4.find()) {
				System.out.println(matcher4.group(1));
			}
			if (matcher5.find()) {
				System.out.println(matcher5.group(1));
			}

			if (matcher7.find()) {
				builder.append(matcher7.group(1));
			}
			if (matcher6.find()) {
				builder.append("&ChannelID=").append(matcher6.group(1));
			}
			builder.append("&randno=").append(Math.random());
			builder.append("&presearchword=faguifenleihao=").append(
					matcher5.group(1));

			if (matcher8.find()) {
				builder.append("&filename=").append(matcher8.group(1))
						.append(".doc");
			}
			System.out.println(builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
