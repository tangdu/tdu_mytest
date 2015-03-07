package net.tdu.tika;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.microsoft.OfficeParser;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * 用tika 来提取文本内容
 * 只做为提取内容工具包
 * 
 * @author tangdu
 * 
 * @time 2013-3-28 下午3:36:03
 */

public class PdfTika {

	StringWriter stringWriter = new StringWriter();
	Writer writer = new BufferedWriter(stringWriter);
	ContentHandler handler = new BodyContentHandler(writer);
	Metadata metadata = new Metadata();
	ParseContext context = new ParseContext();
	Parser parser = null;

	private void parse(InputStream inputStream) {
		try {
			parser.parse(inputStream, handler, metadata, context);
			System.out.println(stringWriter.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提取pdf内容 1m-3.5S 有乱码,内容需要去躁
	 */
	@Test
	public void recoverPdf() {
		parser = new PDFParser();
		try {
			InputStream inputStream = new FileInputStream(new File(
					"C:\\Users\\tangdu\\Downloads\\操作风险管理概论及三大工具.pdf"));
			/*
			 * InputStream
			 * inputStream=PdfTika.class.getClassLoader().getResourceAsStream(
			 * "PDFbox使用教程.pdf");
			 */
			parse(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stringWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 提取office内容,目前无乱码。 1M-1.5S 左右 文字比较多
	 */
	@Test
	public void recoverWord() {
		parser = new OfficeParser();
		try {
			InputStream inputStream = new FileInputStream(new File(
					"F:\\source\\阅读材料\\项目实施九阴真经.doc"));
			parse(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				stringWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 提取excel内容 ，目前无乱码。
	 * 0.1M -1.3S 
	 */
	@Test
	public void recoverExcel(){
		try {
			parser = new OOXMLParser();
			InputStream inputStream = PdfTika.class.getClassLoader().getResourceAsStream("excel.xlsx");
			parse(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
