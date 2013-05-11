package net.tdu.spider.craw;

import org.junit.Test;

/**
 * 法律法规库抓取 测试可行性：
 * 
 * 1.能够取得查询法规库内所有制度的URL 
 * 2.URL能被程序访问，是否支持HTTP协议
 * 3.更新的制度是否存在于更新库中，这样就能便于定期更新。就不需要整个法规库进行抓取，而只要根据更新库中（判断文件更新，取得header中的最后修改时间）
 * 4.所有制度文件显示是，是否能分页解析每个URL，取得每个制度文件的基本信息（生效时间，分类号，内容分类。。。）
 * 5.是否能通过拼装URL（url+flid=分类号） ,链接到法规库中。找到对应的制度文件
 * 6.确定文件更新机制，（更新的制度文件，它的分类号会发生改变不）
 * 
 * @author tangdu
 * 
 * @time 2013-4-11 上午7:39:06
 */
public class RuleCraw {

	private static final String index_url = "";// 查询所有首页信息
	private static final String update_index_url = "";// 查询更新库首页信息
	private static final String file_url = "";// 单独制度文件信息

	@Test
	public void run(){
		String tmp="trsbro://outline?ChannelID=18&randno=27882&templet=&resultid=1";
		
	}
}
