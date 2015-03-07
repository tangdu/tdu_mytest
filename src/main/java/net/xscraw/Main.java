package net.xscraw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 对于特定页面的抓取情况
 * 
 * @author tangdu
 *
 */
public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class);

	class Page {
		public Integer sno;
		public String name;
		public String content;

		public Page(String name, String content, Integer sno) {
			this.name = name;
			this.content = content;
			this.sno = sno;
		}
	}

	public static void main(String[] args) {
		new Main().craw();
	}

	static String base = "http://www.jdxs.net/files/article/html/109/109422/";
	List<Page> data = new ArrayList<Page>();

	public void craw() {

		long start = System.currentTimeMillis();
		// step1: 解析首页 得到所有子连接
		String index = base + "index.html";
		String content = HttpClientUtils.getDataStr(index);
		Elements links = parseIndex(content);
		LOGGER.error("total page count is " + links.size());
		// step2: 多线程抓取 保存本地
		BlockingQueue<Runnable> are = new ArrayBlockingQueue<Runnable>(2);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 700, 4,
				TimeUnit.DAYS, are);
		Element ele = null;
		for (int i = 0; i < 100; i++) {
			ele = links.get(i);
			executor.execute(new PageCraw(base + ele.attr("href"), ele.text()));
		}
		executor.shutdown();
		System.out.println(data.size() + "----" + links.size());
		
		try {
			boolean loop=false;
			do  {
				loop=!executor.awaitTermination(30, TimeUnit.SECONDS);
			} while(loop);
			
			if(!loop){
				// step3: 将数据排序组合成文档
				format();
				genrate();
				
				long end = System.currentTimeMillis();
				LOGGER.error("exec time " + (end - start) / 1000 + "s");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static final Pattern PATTERN = Pattern.compile("第([0-9]{1,})章.*");

	class PageCraw implements Runnable {
		String url;
		String name;
		Integer sno;

		public PageCraw(String url, String name) {
			this.url = url;
			this.name = name;
			Matcher matcher = PATTERN.matcher(this.name);
			if (matcher.find()) {
				sno = Integer.valueOf(matcher.group(1));
			}
		}

		public void run() {
			try {
				Thread.sleep(9000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String content = HttpClientUtils.getDataStr(this.url);
			if (StringUtils.isBlank(content)) {// 重试一次
				content = HttpClientUtils.getDataStr(url);
			}
			if (StringUtils.isBlank(content)) {// 重试二次
				content = HttpClientUtils.getDataStr(url);
			}
			if (StringUtils.isBlank(content)) {// 重试三次
				content = HttpClientUtils.getDataStr(url);
			}
			if (StringUtils.isNotBlank(content)) {
				Page page = new Page(this.name, parsePage(content), this.sno);
				synchronized (data) {
					data.add(page);
				}
			}
		}
	}

	public Elements parseIndex(String content) {
		Document doc = Jsoup.parse(content);
		return doc.select("table[id=Table1] td a");
	}

	public String parsePage(String content) {
		Document doc = Jsoup.parse(content);
		String tent=doc.select("div[id=zoom]").html();
		if(tent!=null){
			tent=tent.replaceAll("<br>","\n").replaceAll("<br />","").replaceAll("&nbsp;", "");
			return tent.replaceAll("本站7&times;24小时不间断超速小说更新,请牢记经典小说网址:", "")
					.replaceAll("wap.jdxs.net</b>【经典小说】，TXT小说下载请到小说信息页，请点上面的“返回书页”！", "")
					.replaceAll("www.jdxs.net</b>\\[拼音第一个字母\\]手机看小说:", "")
					.replaceAll("复制本地址到浏览器看最新章节%77%77%77%2E%6A%64%78%73%2E%6E%65%74 ", "")
					.replaceAll("<b>", "").replaceAll("@", "");
		}
		return tent;
	}

	public void format() {

	}

	public void genrate() {
		File file = new File("./都市之最强学生.txt");
		if(file.exists()){
			file.delete();
		}
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			for (Page p : data) {
				bufferedWriter.write(p.name+"\n");
				bufferedWriter.write(p.content);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		} finally {
			try {
				bufferedWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
