package net.m163;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * my userid =2548841662261727733
 * @author tangdu
 *
 * @time 2013-12-11 下午8:11:33
 */
public class SendMsg {
	public static final String UA = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36";

	
	CloseableHttpClient client = HttpClients.createDefault();
	
	public static void main(String[] args) {
		try {
			SendMsg msg=new SendMsg();
			msg.login();
			//msg.newCount();
			//
			//msg.index();
			msg.sendMsg();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void newCount()throws Exception{
		HttpPost httpPost = new HttpPost("http://love.163.com/newCount");
		httpPost.addHeader("User-Agent", UA);
		httpPost.addHeader("Cookie", "_ntes_nuid=a77e342bd5f7691d60469951c002169f; usertrack=ezq041I1CU+frHTpOk7hAg==; NTES_REPLY_NICKNAME=tangdu0228yes%40163.com%7CTD%E6%9C%A8%E5%AD%90%E9%B1%BC163%7C2548841662261727733%7C4914927338%7C%7CHK9GI7uE90CMtdwDoEMbmCjM9il7UzwSq1Qg_6UUfnwuFjMNF3qU1PYQxP_QDBFTN7dM0W3pXpjQbaqH51f0xXHI8uovSYF3b5sJaolidoQb6%7C1%7C2; vjuids=120bc8007.1412154e131.0.23475b1e; newVisitor_v130711=1; hasShowPaidEmotion=1; isShowChartletTips=1; pgv_pvi=8670001152; followNoTip=1; hasShowPaidEmotion_1203=1; isShowChartletTips_1203=1; LTINFO=21946463|211009||2:51fanli; NTES_PASSPORT=EXbx5B3ui0CCoPhkulnZoRCRP.UkeTUx0Xq31Skh5ngFRcNfRVhy3uHv_uWvwnRXfsUZtCvP1R.iGvOhSYnQ_.E.v8Eutc1rVEtDn2D_eeq_7; P_INFO=tangdu0228yes@163.com|1386591761|1|mail163|11&14|zhj&1386591704&mail_newmsg#zhj&330100#10#0#0|152950&0|smtp163&mail163&yuehui&blog|tangdu0228yes@163.com; NTES_SESS=Pu07suf3JlBd2426fBBrde2sto8FaZaGvK4WkMk_76dwofaqo8VJbMnRdMmRzrotqGVccKEKS3XQqMsiEggs5qHoSLeGFXkuwnw_1r_Ornjeil5n3O4Ha.MzMJ28EnxsD9ghXT9z_ctA0B.a1B8rS07zMIXLL88JXXTS3WOF6WRH5; S_INFO=1386760817|1|0&80##|tangdu0228yes#tdmax_hn; ANTICSRF=4fd9dc69c260476fd4642f523c3302a2; user-from=http://news.163.com/special/163mail_2012/?color=003399; from-page=http://news.163.com/special/163mail_2012/?color=003399; vjlast=1385797738.1386760921.11; cm_newmsg=user%3Dtdmax_hn%40163.com%26new%3D1%26total%3D8; __utma=44888505.1860197979.1385797780.1386669119.1386761002.14; __utmb=44888505.5.10.1386761002; __utmc=44888505; __utmz=44888505.1386761002.14.12.utmcsr=news.163.com|utmccn=(referral)|utmcmd=referral|utmcct=/special/163mail_2012/; _ntes_nnid=57f8913b5862822672909c9619ab0b74,1386760920847; NETEASE_WDA_UID=2548841662261727733%23%7C%231358574655044");
		httpPost.addHeader("Host", "love.163.com");
		httpPost.addHeader("Origin", "http://love.163.com");
		httpPost.addHeader("Referer", "http://love.163.com/messages");
		httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpPost.addHeader("Connection", "keep-alive");
		
		CloseableHttpResponse response =client.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println(EntityUtils.toString(entity,"UTF-8"));
			}
		}else{
			System.out.println(response.getStatusLine().getStatusCode()+"_CODE");
		}
	}
	
	public void sendMsg()throws Exception{
		HttpPost httpPost = new HttpPost("http://love.163.com/messages/add");
		httpPost.addHeader("User-Agent", UA);
		//httpPost.addHeader("Cookie", "_ntes_nuid=a77e342bd5f7691d60469951c002169f; usertrack=ezq041I1CU+frHTpOk7hAg==; NTES_REPLY_NICKNAME=tangdu0228yes%40163.com%7CTD%E6%9C%A8%E5%AD%90%E9%B1%BC163%7C2548841662261727733%7C4914927338%7C%7CHK9GI7uE90CMtdwDoEMbmCjM9il7UzwSq1Qg_6UUfnwuFjMNF3qU1PYQxP_QDBFTN7dM0W3pXpjQbaqH51f0xXHI8uovSYF3b5sJaolidoQb6%7C1%7C2; vjuids=120bc8007.1412154e131.0.23475b1e; newVisitor_v130711=1; hasShowPaidEmotion=1; isShowChartletTips=1; pgv_pvi=8670001152; followNoTip=1; hasShowPaidEmotion_1203=1; isShowChartletTips_1203=1; LTINFO=21946463|211009||2:51fanli; NTES_PASSPORT=EXbx5B3ui0CCoPhkulnZoRCRP.UkeTUx0Xq31Skh5ngFRcNfRVhy3uHv_uWvwnRXfsUZtCvP1R.iGvOhSYnQ_.E.v8Eutc1rVEtDn2D_eeq_7; P_INFO=tangdu0228yes@163.com|1386591761|1|mail163|11&14|zhj&1386591704&mail_newmsg#zhj&330100#10#0#0|152950&0|smtp163&mail163&yuehui&blog|tangdu0228yes@163.com; NTES_SESS=Pu07suf3JlBd2426fBBrde2sto8FaZaGvK4WkMk_76dwofaqo8VJbMnRdMmRzrotqGVccKEKS3XQqMsiEggs5qHoSLeGFXkuwnw_1r_Ornjeil5n3O4Ha.MzMJ28EnxsD9ghXT9z_ctA0B.a1B8rS07zMIXLL88JXXTS3WOF6WRH5; S_INFO=1386760817|1|0&80##|tangdu0228yes#tdmax_hn; ANTICSRF=4fd9dc69c260476fd4642f523c3302a2; user-from=http://news.163.com/special/163mail_2012/?color=003399; from-page=http://news.163.com/special/163mail_2012/?color=003399; vjlast=1385797738.1386760921.11; cm_newmsg=user%3Dtdmax_hn%40163.com%26new%3D1%26total%3D8; __utma=44888505.1860197979.1385797780.1386761002.1386763938.15; __utmb=44888505.2.10.1386763938; __utmc=44888505; __utmz=44888505.1386761002.14.12.utmcsr=news.163.com|utmccn=(referral)|utmcmd=referral|utmcct=/special/163mail_2012/; _ntes_nnid=57f8913b5862822672909c9619ab0b74,1386760920847; NETEASE_WDA_UID=2548841662261727733%23%7C%231358574655044");
		httpPost.addHeader("Host", "love.163.com");
		httpPost.addHeader("Origin", "http://love.163.com");
		httpPost.addHeader("Referer", "http://love.163.com/messages");
		httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpPost.addHeader("Connection", "keep-alive");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("withUserId", "1174778620820642529"));
		nvps.add(new BasicNameValuePair("content", "可以要到你的QQ嘛，小姑娘"));
		nvps.add(new BasicNameValuePair("isSetTop", "1"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		
		CloseableHttpResponse response =client.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println(EntityUtils.toString(entity,"UTF-8"));
			}
		}else{
			System.out.println(response.getStatusLine().getStatusCode()+"_CODE");
		}
	}
	
	public void index() throws Exception{
		HttpGet httpGet = new HttpGet("http://love.163.com/messages");
		httpGet.addHeader("User-Agent", UA);
		CloseableHttpResponse response =client.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println(EntityUtils.toString(entity,"UTF-8"));
			}
		}else{
			System.out.println(response.getStatusLine().getStatusCode()+"_CODE");
		}
	}
	
	public void login() throws Exception{
		HttpPost httpPost = new HttpPost("https://reg.163.com/logins.jsp");
		httpPost.addHeader("User-Agent", UA);
		//httpPost.addHeader("Cookie", "_ntes_nuid=a77e342bd5f7691d60469951c002169f; usertrack=ezq041I1CU+frHTpOk7hAg==; NTES_REPLY_NICKNAME=tangdu0228yes%40163.com%7CTD%E6%9C%A8%E5%AD%90%E9%B1%BC163%7C2548841662261727733%7C4914927338%7C%7CHK9GI7uE90CMtdwDoEMbmCjM9il7UzwSq1Qg_6UUfnwuFjMNF3qU1PYQxP_QDBFTN7dM0W3pXpjQbaqH51f0xXHI8uovSYF3b5sJaolidoQb6%7C1%7C2; vjuids=120bc8007.1412154e131.0.23475b1e; pgv_pvi=8670001152; T_INFO=8058F798F8442BBEA539DED22667058B; LTINFO=21946463|211009||2:51fanli; SID=0d35a2ac-d5a9-4a69-b872-9bd6fe5f2d08; JSESSIONID=dabGdEjgC8ell8vOSyHlu; vjlast=1385797738.1386760921.11; P_INFO=tangdu0228yes@163.com|1386591761|2|mail163|11&14|zhj&1386591704&mail_newmsg#zhj&330100#10#0#0|152950&0|smtp163&mail163&yuehui&blog|tangdu0228yes@163.com; _ntes_nnid=57f8913b5862822672909c9619ab0b74,1386760920847");
		httpPost.addHeader("Host", "reg.163.com");
		httpPost.addHeader("Origin", "http://love.163.com");
		httpPost.addHeader("Referer", "http://love.163.com/");
		httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpPost.addHeader("Connection", "keep-alive");
		httpPost.addHeader("Cache-Control", "max-age=0");
		httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "tangdu0228yes@163.com"));
		nvps.add(new BasicNameValuePair("password", "u5510u675c_"));
		nvps.add(new BasicNameValuePair("savelogin", "1"));
		nvps.add(new BasicNameValuePair("url", "http://love.163.com:80/"));
		nvps.add(new BasicNameValuePair("product", "ht"));
		nvps.add(new BasicNameValuePair("type", "1"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		
		CloseableHttpResponse response =client.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				System.out.println(EntityUtils.toString(entity,"UTF-8"));
			}
		}else{
			System.out.println(response.getStatusLine().getStatusCode()+"_CODE");
		}
	}
}
