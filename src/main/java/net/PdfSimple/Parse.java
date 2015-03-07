package net.PdfSimple;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;

/**
 * PDF解析父类
 * User: tangdu
 * Date: 13-9-30
 * Time: 下午7:52
 * To change this template use File | Settings | File Templates.
 */
public class Parse {

    private PDDocument pdfdocument;

    /**
     * 构造
     *
     * @param file
     * @throws PdfParseException
     */
    public  Parse(String file) throws PdfParseException {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(new File(file));
            PDFParser parser = new PDFParser(inputStream);
            parser.parse();
            this.pdfdocument = parser.getPDDocument();
        } catch (FileNotFoundException e) {
            throw new PdfParseException(0, e.getMessage());
        } catch (IOException e) {
            throw new PdfParseException(1, e.getMessage());
        }
    }

    /**
     * 得到文本内容
     *
     * @return
     */
    public String getText() {
        if (pdfdocument != null) {
            try {
                PDFTextStripper ts = new PDFTextStripper();
                return ts.getText(pdfdocument);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 关闭流
     */
    public void close() {
        if (pdfdocument != null) {
            try {
                pdfdocument.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
