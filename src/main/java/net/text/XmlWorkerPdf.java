package net.text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;

import org.w3c.tidy.Tidy;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class XmlWorkerPdf {
	static String ROOT="C:\\Users\\杜\\";
	static String PATH=null;
	static{
		String t=XmlWorkerPdf.class.getClassLoader().getResource("./net/text/xmlworker.html").getFile();
		File _f=new File(t);
		PATH=_f.getParent();
		System.out.println(PATH);
	}
	public static String htmlClean(String path){
		String _path=PATH+"//out.html";
		try {
			Tidy tidy = new Tidy();
			//tidy.setPrintBodyOnly(true); 
			tidy.setDocType("3");
			tidy.setXHTML(true);  
			tidy.setShowErrors(3);
			tidy.setShowWarnings(false);
			tidy.setInputEncoding("utf-8");   
			FileInputStream in = new FileInputStream(path);
			FileOutputStream out = new FileOutputStream(_path); //输出的文件  
			tidy.parse(in, out); 
			out.close(); 
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return _path;
	}
	public static void main(String[] args) {
		try {
			Document document = new Document();
			 PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(new File(ROOT+"cccc.pdf")));
			 document.open();
			/*BaseFont baseFont=BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",false);
			Font font=new Font(baseFont,12,Font.NORMAL,BaseColor.RED);
			Paragraph paragraph=new Paragraph("开始..");
			paragraph.add(new Paragraph("大家好",font));
			document.add(paragraph);*/
			 XMLWorkerHelper.getInstance().parseXHtml(writer, document,
		                new FileInputStream(htmlClean(PATH+"//xmlworker.html")), Charset.forName("UTF-8"));
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
