package net.tdu.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.Test;

import com.itextpdf.text.pdf.PdfReader;

/**
 * 使用pdfbox 解析 pdf
 * pdfbox 存在乱码现象
 * 
 * 注意 itext 也可用来读取pdf,操作Pdf
 * 
 * @author tangdu
 * 
 * @time 2013-3-28 下午4:04:21
 */
public class PdfParse {

	@Test
	public  void pdfboxParser() {
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(
					"C:\\Users\\tangdu\\Downloads\\Java程序员进化为架构师掌握的知识_IT168文库.pdf"));
			PDFParser parser = new PDFParser(inputStream);
			parser.parse();
			PDDocument pdfdocument = parser.getPDDocument();
			PDFTextStripper ts = new PDFTextStripper();
			String content = ts.getText(pdfdocument);
			System.out.println(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void itextpdfParse(){
		try {
			InputStream inputStream=new FileInputStream(new File(
					"C:\\Users\\tangdu\\Downloads\\操作风险管理概论及三大工具.pdf"));
			PdfReader pdfReader=new PdfReader(inputStream);
			//TODO 未完成
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
