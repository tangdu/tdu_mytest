package net.tdu.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

/**
 * 使用apache commons email 发生邮件
 * 
 * @author tangdu
 * 
 * @time 2013-3-28 下午4:43:56
 */
public class EmailUtil {

	@Test
	public void test01() {
		Email email = new SimpleEmail();
		email.setHostName("smtp.163.com");//imap.163.com
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("tangdu0228yes", ""));
		email.setSSLOnConnect(true);
		try {
			email.setFrom("tangdu0228yes@163.com");
			email.setSubject("TestMail");
			//email.setMsg("This is a test mail ...嘿嘿 :-)");存在乱码
			email.setContent("This is a test mail ...嘿嘿 :-)", "text/plain;charset=UTF-8");
			email.addTo("1160755272@qq.com");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
