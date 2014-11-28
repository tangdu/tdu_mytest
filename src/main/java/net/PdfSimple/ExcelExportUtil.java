package net.PdfSimple;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Excel导出
 * User: tangdu
 * Date: 13-9-30
 * Time: 下午10:50
 * To change this template use File | Settings | File Templates.
 */
public class ExcelExportUtil {

    private HSSFWorkbook wb=null;
    private HSSFSheet sheet =null;
    private XSSFWorkbook wb3=null;
    private XSSFSheet sheet3 =null;
    private int rowIndex=0;
    private boolean is2007=true;

    public ExcelExportUtil(boolean is2007,String titles[]){
        if(is2007){
            wb3 = new XSSFWorkbook();
            sheet3 = wb3.createSheet("Export");

            if(titles!=null){
                XSSFRow xssfRow=sheet3.createRow(rowIndex);
                for (int i = 0; i < titles.length; i++) {
                    XSSFCell xssfCell = xssfRow.createCell(i);
                    xssfCell.setCellValue(titles[i]);
                    //标题水平、垂直居中
                    XSSFCellStyle ce1 = wb3.createCellStyle();
                    ce1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                    ce1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
                    ce1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
                    xssfCell.setCellStyle(ce1);
                }
                rowIndex=1;
            }
        }else{
            wb = new HSSFWorkbook();
            sheet = wb.createSheet("Export");

            if(titles!=null){
                HSSFRow hssfRow=sheet.createRow(rowIndex);
                for (int i = 0; i < titles.length; i++) {
                    HSSFCell hssfCell = hssfRow.createCell(i);
                    hssfCell.setCellValue(titles[i]);
                    //标题水平、垂直居中
                    HSSFCellStyle ce1 = wb.createCellStyle();
                    ce1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                    ce1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                    ce1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    ce1.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
                    hssfCell.setCellStyle(ce1);
                }
                rowIndex=1;
            }
        }
        this.is2007=is2007;
    }


    public void addRow(List rows){
        if(rows!=null){
            if(is2007){
                XSSFRow xssfRow=sheet3.createRow(rowIndex++);
                for(int i=0;i<rows.size();i++){
                    XSSFCell xssfCell =xssfRow.createCell(i);
                    xssfCell.setCellValue(rows.get(i).toString());
                }
            }else{
                HSSFRow hssfRow=sheet.createRow(rowIndex++);
                for(int i=0;i<rows.size();i++){
                    HSSFCell hssfCell =hssfRow.createCell(i);
                    hssfCell.setCellValue(rows.get(i).toString());
                }
            }

        }
    }

    /**
     * 保存文件
     * @param path
     */
    public void save(String path){
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            if(is2007){
                wb3.write(fileOut);
            }else{
                wb.write(fileOut);
            }

            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void export(){
    }

    public String getCellStringValue(HSSFCell cell) {
        String cellValue = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING://字符串类型
                cellValue = cell.getStringCellValue();
                if(cellValue.trim().equals("")||cellValue.trim().length()<=0)
                    cellValue=" ";
                break;
            case HSSFCell.CELL_TYPE_NUMERIC: //数值类型
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA: //公式
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                cellValue=" ";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                break;
            default:
                break;
        }
        return cellValue;
    }
}
