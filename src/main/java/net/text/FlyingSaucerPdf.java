package net.text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;



public class FlyingSaucerPdf {
	static String PATH=null;
	static{
		String t=XmlWorkerPdf.class.getClassLoader().getResource("./net/text/flyingsaucer.html").getFile();
		File _f=new File(t);
		PATH=_f.getParent();
		System.out.println(PATH);
	}
	
	public static void main(String[] args) {
        OutputStream os = null;
        try {
        	DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(new File(HtmlUtil.htmlClean(PATH+"//flyingsaucer.html")));
        	ITextRenderer renderer = new ITextRenderer();
        	renderer.setDocument(doc, null);
            os = new FileOutputStream(PATH+"//fly.pdf");
            renderer.layout();
            renderer.createPDF(os);
            os.close();
            os = null; 
        }  catch (Exception e) {
        	e.printStackTrace();
        }finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                   e.printStackTrace();
                }
            }
        }
	}
}
