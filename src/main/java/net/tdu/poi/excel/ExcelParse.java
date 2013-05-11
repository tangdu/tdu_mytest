package net.tdu.poi.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * HSSF － 提供读写Microsoft Excel XLS格式档案的功能。 XSSF － 提供读写Microsoft Excel OOXML
 * XLSX格式档案的功能。 HWPF － 提供读写Microsoft Word DOC格式档案的功能。 HSLF － 提供读写Microsoft
 * PowerPoint格式档案的功能。 HDGF － 提供读Microsoft Visio格式档案的功能。 HPBF － 提供读Microsoft
 * Publisher格式档案的功能。 HSMF － 提供读Microsoft Outlook格式档案的功能。
 * 
 * The supplied data appears to be in the Office 2007+ XML. You are calling the
 * part of POI that deals with OLE2 Office Documents. You need to call a
 * different part of POI to process this data (eg XSSF instead of HSSF)
 * 
 * @author tangdu
 * 
 * @time 2013-3-19 下午1:35:39
 */
public class ExcelParse {

	private static final String FILE_NAME = "test.xlsx";

	@Test
	public void parseExcel() {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(this.getClass()
					.getResourceAsStream(FILE_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
