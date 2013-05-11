package net.tdu.lucene.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

/**
 * 文件视图类
 * 
 * @author tangdu
 * 
 */
public class FileView {

	private List<File> files;
	private static FileView FILEVIEW = null;

	private FileView() {
		files = new ArrayList<File>();
	}

	public static FileView getInstance() {
		if (FILEVIEW == null) {
			FILEVIEW = new FileView();
		}
		return FILEVIEW;
	}

	private void findFiles(String filepath, FileFilter fileFilter) {
		File file = new File(filepath);
		if (file.canRead()) {
			if (file.isDirectory()) {
				for (File file_ : file.listFiles()) {
					findFiles(file_.getPath(), fileFilter);
				}
			} else {
				if (fileFilter != null) {
					if (fileFilter.handler(file)) {
						files.add(file);
					}
				} else {
					files.add(file);
				}
			}
		}
	}

	private void findFiles(String filepath, FileFilter fileFilter,
			FileHandler fileHandler) throws FileNotFoundException, IOException {
		File file = new File(filepath);
		if (file.canRead()) {
			if (file.isDirectory()) {
				for (File file_ : file.listFiles()) {
					findFiles(file_.getPath(), fileFilter, fileHandler);
				}
			} else {
				if (fileFilter != null) {
					if (fileFilter.handler(file)) {
						fileHandler.hander(file);
					}
				} else {
					fileHandler.hander(file);
				}
			}
		}
	}

	/**
	 * 所有的文件
	 * 
	 * @param filepath
	 * @return
	 */
	public List<File> getFiles(String filepath) {
		findFiles(filepath, null);
		return files;
	}

	/**
	 * 文件过滤
	 * 
	 * @param filepath
	 * @param fileFilter
	 * @return
	 */
	public List<File> getFiles(String filepath, FileFilter fileFilter) {
		findFiles(filepath, fileFilter);
		return files;
	}

	/**
	 * 文件处理、过滤
	 * 
	 * @param filepath
	 * @param fileFilter
	 * @param fileHandler
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void execute(String filepath, FileFilter fileFilter,
			FileHandler fileHandler) throws FileNotFoundException, IOException {
		findFiles(filepath, fileFilter, fileHandler);
	}

	/**
	 * 文件处理
	 * 
	 * @param filepath
	 * @param fileHandler
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void execute(String filepath, FileHandler fileHandler)
			throws FileNotFoundException, IOException {
		findFiles(filepath, null, fileHandler);
	}

	/**
	 * 按编码读取文件
	 * 
	 * @param filepath
	 * @param encoding
	 * @return
	 */
	public String readFile(String filepath, String encoding) {
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = new FileInputStream(filepath);
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, encoding);
			bufferedReader = new BufferedReader(inputStreamReader);
			String line = "";
			StringBuilder builder = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line).append("\n");
			}
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 例用httpparse 得到文本内容-有空格，有回车，测试为原始字符
	 * 
	 * @param inputHTML
	 * @return
	 * @throws ParserException
	 */
	public String getTextByHttpParse(String inputHTML) {
		StringBuffer text;
		try {
			Parser parser = new Parser();
			parser.setInputHTML(inputHTML);
			parser.setEncoding(parser.getURL());
			HtmlPage page = new HtmlPage(parser);
			parser.visitAllNodesWith(page);
			NodeList list = page.getBody();

			text = new StringBuffer();
			for (NodeIterator iterator = list.elements(); iterator
					.hasMoreNodes();) {
				Node node = iterator.nextNode();
				text.append(node.toPlainTextString());
			}
			return text.toString();
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 用正则实现提取所有文本内容 
	 * 
	 * 网页去躁
	 * @param inputHTML
	 * @return
	 */
	public String getTextByRegex(String inputHTML) {
		if(inputHTML!=null){
			return inputHTML
					.replaceAll("<script[^>]*?>[\\s\\S]*?<\\/script>", "")//去掉脚本
					.replaceAll("<style[^>]*?>[\\s\\S]*?<\\/style>", "")//去掉样式
					.replaceAll("<.*?[^<>]+>", "")//去网页标记
					.replaceAll("\\s|\\n|\\t", " ")//去空格、回车-保留一个空格是为了分词效果
					
					.replaceAll("-", "")//特殊需求，一般地
					.replaceAll("\\*", "");
					
					
		}
		return null;
	}

	/**
	 * 按编码写入文件
	 * 
	 * @param filepath
	 * @param content
	 * @param encoding
	 * @return
	 */
	public boolean writeFile(String filepath, String content, String encoding) {
		BufferedWriter bufferedWriter = null;
		try {
			OutputStream outputStream = new FileOutputStream(filepath);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					outputStream, encoding);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			bufferedWriter.write(content);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws ParserException {
		FileView f = FileView.getInstance();
		System.out.println(f.getTextByRegex(f.readFile(
				"I:\\个人征信新版模板\\2012090500001122007863.html", "gb2312")));
	}
}
