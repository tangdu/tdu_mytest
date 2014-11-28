package net.tdu.httpClient.zbj;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * Created by tangdu on 2014-05-05.
 */
public class QQCraw {
    public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";
    @Test
    public void main() throws Exception{
         CloseableHttpClient closeableHttpClient= HttpClients.custom()
                .setUserAgent(UA).build();
        HttpGet httpGet=new HttpGet("http://xui.ptlogin2.qq.com/cgi-bin/qlogin");
        CloseableHttpResponse response = closeableHttpClient
                .execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            System.out.println("success");
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String cnt = EntityUtils.toString(entity);
                System.out.println(cnt);
            }
        } else {
            System.out.println("error");
        }
    }
}
