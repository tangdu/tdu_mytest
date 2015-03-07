package net.text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.w3c.tidy.Tidy;

public class HtmlUtil {
	static String PATH=null;
	static{
		String t=XmlWorkerPdf.class.getClassLoader().getResource("./net/text/HtmlUtil.class").getFile();
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
}
