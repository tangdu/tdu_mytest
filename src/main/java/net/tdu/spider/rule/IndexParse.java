package net.tdu.spider.rule;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.tdu.spider.parse.Parse;

import org.junit.Test;

/**
 * 法规库首页解析
 * 
 * @author tangdu
 * 
 * @time 2013-4-12 下午9:41:12
 */
public class IndexParse extends Parse{
	private static final String _FILE = "craw_index";

	/**
	 * 具体某个条例URL：trsbro://detail?record=10&ChannelID=18&randno=6878&resultid=1
	 * 分页URL：trsbro://outline/?ChannelID=18&randno=21196&templet=&resultid=1&page=2
	 * 关于测试页面规律：
	 * 1.randno是随机数- 好像是1-30000范围
	 * 2.record代码表序号-（应该是数据库对某个类型下按日期倒序加的排序号）
	 * 3.page 代码分页
	 * 4.ChannelID(也许对应是类型-比如说更新库、XX库
	 * 5.resultid（结果集Id） templet(模板标识)
	 * 
	 * 特别注意不同模板是否展示不一样?是否法规库管理员会调整展示模板或定制?
	 * 在不同的网段上问题?
	 */
	//
	
	
	/**
	 * 匹配页面详情URL
	 */
	@Test
	public void testDetailsParse() {
		//step1:通过正则找出URL
		//step2:找出标题和对应URL
		//step3:找出库对应的标识（待定）
		
		Pattern pattern=Pattern.compile("var\\s*url_rec=\"(.*?)\";countrec\\(rec,trsnum\\);\\s*\\n*</script>(.*?)</a>&nbsp;");
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(_FILE);
		try {
			String htmlSrc = super.getContent(inputStream);
			//去掉空格回车
			htmlSrc=htmlSrc.replaceAll("\\s\\n", "");
			Matcher matcher0 = pattern.matcher(htmlSrc);
			int i=0;
			while(matcher0.find()) {
				System.out.println(++i);
				System.out.println("url-"+matcher0.group(1));
				System.out.println("title-"+matcher0.group(2));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 匹配页面分页URL
	 * var curpage="1";当前页数
	 * var pagenum="5";共页数
	 * var recnum="140";总记录数
	 */
	@Test
	public void testOutLineParse() {
		//step1:找出分页规律-page标识
		//step2:找出共有多少页
		//step3:分页进行采集
		
		//找出分类号
		//找出分页url
		
		
		Pattern curpagePt=Pattern.compile("var\\s+curpage=\"([0-9]{1,})\";");//当前页数
		Pattern pagenumPt=Pattern.compile("var\\s+pagenum=\"([0-9]{1,})\";");//总页数
		Pattern recnumPt=Pattern.compile("var\\s+recnum=\"([0-9]{1,})\";");//总记录数
		
		Pattern cidPt=Pattern.compile("var\\s+cid=\"(.*?)\";");//mapping ChannelID-通道类型
		Pattern chanamePt=Pattern.compile("var\\s+chaname=\"(.*?)\";");//通道名称
		Pattern pageURLPt=Pattern.compile("var\\s+url_gotopage=\"(.*?)\";");//分页URL基础连接
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(_FILE);
		try {
			String htmlSrc = super.getContent(inputStream);
			Matcher curpageMc = curpagePt.matcher(htmlSrc);
			Matcher pagenumMc = pagenumPt.matcher(htmlSrc);
			Matcher recnumMc = recnumPt.matcher(htmlSrc);
			
			Matcher cidMc = cidPt.matcher(htmlSrc);
			Matcher chanameMc = chanamePt.matcher(htmlSrc);
			Matcher pageURLMc = pageURLPt.matcher(htmlSrc);
			if(curpageMc.find()){
				System.out.println("当前页数:"+curpageMc.group(1));
			}
			if(pagenumMc.find()){
				System.out.println("总页数:"+pagenumMc.group(1));
			}
			if(recnumMc.find()){
				System.out.println("总记录数:"+recnumMc.group(1));
			}
			if(cidMc.find()){
				System.out.println("通道类型:"+cidMc.group(1));
			}
			if(chanameMc.find()){
				System.out.println("通道名称:"+chanameMc.group(1));
			}
			if(pageURLMc.find()){
				System.out.println("分页URL基础连接:"+pageURLMc.group(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
