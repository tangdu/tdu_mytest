package net.PdfSimple;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * 把这一类固定格式的pdf数据挖掘出来成为csv文件或xls文件或sqilte文件， 主要关注： 参考价格。
 如果有同样公司名字，但是不同到期时间的，也视为不同的。
 数据样本看下面图片链接！！！
 可不断拖拽这类格式的pdf文件，进去。
 可搜索某个关键字“地产”， 可按日期，做出曲线走势图（ 主要关注： 参考价格）
 如有看得见，摸得着的针对本次需求的进展，请留言。质优价平可长期。
 * User: tangdu
 * Date: 13-9-30
 * Time: 下午6:57
 * To change this template use File | Settings | File Templates.
 */
public class PdfRead {

    //标题
    private static final Pattern P_TITLE= Pattern.compile("全球债券指标 每日快讯\\s+([0-9]{1,}/[0-9]{1,}/[0-9]{4,})");
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("d/M/Y");
    private static final String D_TS[]=new String[]{
            "美元高息债券/USD High Yield Bond",
            "其他美元债券/Other USD Bond",
            "港元债券<适用于香港资本投资者入境计划>/HKD Bond",
            "可换股债券1<适用于香港资本投资者入境计划>/Convertible Bond",
            "合成人民币债券1/Synthetic Bond",
            "纯人民币债券/RMB Bond"
    };
    private List<Record> datas=new ArrayList<Record>();
    private  Date dtData=null;//数据日期

    @Test
    public  void pdfboxParser() {
        PdfParse parse=null;
        try {
            parse=new PdfParse("F:\\workspace\\jaeespace\\mytest\\src\\main\\java\\net\\PdfSimple\\bond news & price 20130829.pdf");
            String content=parse.getText();
            getData(content);
            getDtData(content);
        } catch (PdfParseException e) {
            e.printStackTrace();
        } finally{
            parse.close();
        }
    }

    private void getData(String content){
        //按类型
        int d_len=D_TS.length;

        //this last
        String l_text=content.substring(content.lastIndexOf(D_TS[d_len-1]));
        getRow(l_text,D_TS[d_len-1]);

        //this middle
        for(int i=1;i<d_len-1;i++){
            l_text=content.substring(content.indexOf(D_TS[i-1]),content.indexOf(D_TS[i]));
            getRow(l_text,D_TS[i]);
        }
        System.out.println("--------------------------total rows: "+datas.size());
        exportExcel();
    }

    public void getRow(String content,String type){
        //按行读取
        String []rows=content.split("\r\n");
        for(String row:rows){
            Record record=new Record();
            record.setType(type);
            record.setDate(dtData);
            String [] dts=row.split("[\\s]");
            List<String> lists=new ArrayList<String>();

            //过滤
            if(dts.length>10){
                for(String rowc:dts){
                    lists.add(rowc);
                }
            }
            record.setRows(lists);
            datas.add(record);
        }
    }

    public void getDtData(String content){
        Matcher matcher=P_TITLE.matcher(content);
        if(matcher.find()){
            try {
                dtData=dateFormat.parse(matcher.group(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void exportExcel(){
        if(datas!=null){
            String [] titles=new String[]{
                    "债券名称/Bond",
                    "债券代码/Ticker",
                    "到期日/Maturity Date",
                    "货币/Currency",
                    "票息/Coupon",
                    "发行日期/Issue Date",
                    "发行规模(亿)/Size",
                    "剩余年期/Maturity Years",
                    "年化回报%/Yield to Maturity",
                    "参考价格2/Price",
                    "标普评级/S&P Rating",
                    "穆迪评级/Moody's Rating",
                    "惠誉评级/Fitch Rating"
            };
            ExcelExportUtil excelExportUtil=new ExcelExportUtil(true,titles);
            for(Record record:datas){
                excelExportUtil.addRow(record.getRows());
            }
            excelExportUtil.save("f:/1.xlsx");
        }
    }
}
