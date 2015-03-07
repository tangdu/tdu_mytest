package net.tdu.spider.rule;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 法律法规测试方案
 * 
 * @author tangdu
 * 
 * @time 2013-4-12 下午9:35:41
 */
public class RuleRun {

	// 主页数据能否采集
	public void indexRun(){
		CloseableHttpClient client=HttpClients.createDefault();
	}
	// 制度文件页面数据能否采集基本信息
	// 确认网页更新库，是否为更新数据-能否在网页中取得header中的标识
}
