package net.dbdoc.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcel {

	private Connection connection;
	XSSFCellStyle setBorder;
	XSSFCellStyle linkStyle;
	static SimpleDateFormat DATEFORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat DATEFORMAT2=new SimpleDateFormat("yyyyMMdd");
	private static Properties PROP=null;
	private String fileName;
	
	//加载数据配置文件
	static{
		try {
			InputStream inputStream=CreateExcel.class.getResourceAsStream("db.properties");
			PROP=new Properties();
			PROP.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			long start=System.currentTimeMillis();
			CreateExcel excel=new CreateExcel();
			excel.createIndex();
			excel.createSheet();
			excel.save();
			long end=System.currentTimeMillis();
			System.out.println((end-start)+" 执行时间 (ms)");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private XSSFWorkbook workbook;
	public CreateExcel() throws IOException, SQLException{
		workbook = new XSSFWorkbook(this.getClass()
				.getResourceAsStream("modelck.xlsx"));
		//边框
		setBorder = workbook.createCellStyle();
		setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		//链接
		linkStyle = workbook.createCellStyle();
		XSSFFont cellFont= workbook.createFont();
		linkStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		linkStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		linkStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		linkStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		cellFont.setUnderline((byte) 1);
		cellFont.setColor(HSSFColor.BLUE.index);
		linkStyle.setFont(cellFont);
		//DB
		DbUtils.loadDriver(PROP.getProperty("jdbc.connection.driver_class"));
		connection=DriverManager
				.getConnection(PROP.getProperty("jdbc.connection.url"),
						PROP.getProperty("jdbc.connection.username"),
						PROP.getProperty("jdbc.connection.password"));
		//文件名称
		fileName=PROP.getProperty("jdbc.connection.username")+"表设计"+DATEFORMAT2.format(new Date());
	}
	
	public void setIndexProp(XSSFSheet sheet,String ...prop){
		XSSFRow row=null;
		XSSFCell cell=null;
		for(int i=0;i<prop.length;i++){
			row=sheet.getRow(i);
		    cell=row.getCell(1);
			cell.setCellValue(prop[i]);
		}
	}
	
	public List<Map<String,Object>>  getTables() throws SQLException{
		QueryRunner queryRunner=new QueryRunner();
		String sql1="select table_name,comments from user_tab_comments ";
		List<Map<String,Object>> lMap=queryRunner.query(connection,sql1 , 
				new MapListHandler());
		return lMap;
	}
	
	public void createIndex() throws SQLException{
		XSSFSheet sheet=workbook.getSheetAt(0);;
		XSSFRow row=null;
		XSSFCell cell=null;
		String tableName=null;
		int k=6;
		for(Map<String,Object> mm:getTables()){
			tableName=mm.get("TABLE_NAME").toString();
			if(!valid(tableName)){
				continue;
			}
			row=sheet.createRow(k);
			//表名
			cell=row.createCell(0);
			cell.setCellStyle(setBorder);
			cell.setCellValue(tableName);
			//生成链接
			cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
			cell.setCellFormula("HYPERLINK(\"["+fileName+".xlsx]'"+tableName+"'!B2\",\""+tableName+"\")");
			cell.setCellStyle(linkStyle);
			//注释
			cell=row.createCell(1);
			cell.setCellValue(mm.get("COMMENTS")==null?"":mm.get("COMMENTS").toString());
			cell.setCellStyle(setBorder);
			k++;
		}
		String user=PROP.getProperty("jdbc.connection.username");
		String date=DATEFORMAT.format(new Date());
		setIndexProp(sheet,user,date,"V1.0");
	}
	
	public boolean valid(String tableName){
		if(tableName.startsWith("BIN")){
			return false;
		}
		return true;
	}
	
	public void setSheetCell(XSSFSheet sheet,String tableName,String tableComments){
		XSSFRow row=sheet.getRow(1);
		XSSFCell cell=row.getCell(1);
		cell.setCellValue(tableName);
		cell=row.getCell(4);
		cell.setCellValue(tableComments);
	}
	
	public void createSheet() throws SQLException{
		QueryRunner queryRunner=new QueryRunner();
		XSSFSheet sheet=null;
		XSSFRow row=null;
		XSSFCell cell=null;
		String tableName=null;
		String tableComments=null;
		Object []yrow=null;
			
		int i=2;
		for(Map<String,Object> mm:getTables()){
			tableName=mm.get("TABLE_NAME").toString();
			tableComments=(mm.get("COMMENTS")==null ?"":mm.get("COMMENTS").toString());
			if(!valid(tableName)){
				continue;
			}
			workbook.cloneSheet(1);
			workbook.setSheetName(i, tableName);
			sheet=workbook.getSheetAt(i);
			//插入表字段说明
			String sql2="SELECT T1.COLUMN_NAME,\n" +
					"       CASE\n" + 
					"         WHEN T1.DATA_SCALE IS NULL THEN\n" + 
					"          T1.DATA_TYPE || '(' || T1.DATA_LENGTH || ')'\n" + 
					"         ELSE\n" + 
					"          T1.DATA_TYPE || '(' || T1.DATA_LENGTH || ',' || T1.DATA_SCALE || ')'\n" + 
					"       END DATA_TYPE,\n" + 
					"       T1.NULLABLE,\n" + 
					"       '' default_value,\n" + 
					"       t2.comments\n" + 
					"  FROM USER_TAB_COLUMNS T1, USER_COL_COMMENTS T2\n" + 
					" WHERE T1.TABLE_NAME = T2.TABLE_NAME\n" + 
					"   AND T1.COLUMN_NAME = T2.COLUMN_NAME and t1.table_name='"+tableName+"'";
			List<Object[]> tabDetails=queryRunner.query(connection,sql2 , 
					new ArrayListHandler());
			
			//设置属性
			setSheetCell(sheet,tableName,tableComments);
			//生成列
			for(int k=0;k<tabDetails.size();k++){
				row=sheet.createRow(k+5);
				yrow=tabDetails.get(k);
				
				for(int j=0;j<yrow.length;j++){
					cell=row.createCell(j);
					cell.setCellStyle(setBorder);
					cell.setCellValue(yrow[j]==null ? "" :yrow[j].toString());
				}
			}
			i++;
		}
		workbook.removeSheetAt(1);
		
	}

	public void close() throws SQLException{
		connection.close();
	}
	
	public void save(){
		try {
			FileOutputStream os = new FileOutputStream("C://Users//杜//"+fileName+".xlsx");
			workbook.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
