package net.dbfm;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: tangdu
 * Date: 13-10-1
 * Time: 上午8:22
 * To change this template use File | Settings | File Templates.
 */
public class FmSongDown {
    public static final String UA = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";
    private static Set<Header> defaultHeaders;
    static {
        defaultHeaders = new HashSet<Header>();
        defaultHeaders.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8"));
        defaultHeaders.add(new BasicHeader("Accept-Encoding", "gzip,deflate,sdch"));
        defaultHeaders.add(new BasicHeader("Cookie", "bid=\"8YicOUp+Kx0\"; __utma=30149280.1987792648.1380583846.1380583846.1380583846.1; __utmc=30149280; __utmz=30149280.1380583846.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=golang%20%E4%B9%B1%E7%A0%81"));
        defaultHeaders.add(new BasicHeader("Accept", "*/*"));
        defaultHeaders.add(new BasicHeader("Referer", "http://douban.fm/"));
        defaultHeaders.add(new BasicHeader("Host", "mr3.douban.com"));
        defaultHeaders.add(new BasicHeader("Connection", "keep-alive"));
    }

    private CloseableHttpClient closeableHttpClient;
    private BasicCookieStore cookieStore;
    private OutputStream outputStream;

    public  FmSongDown(){
        HttpParams params = new BasicHttpParams();
        //设置连接超时时间
        int REQUEST_TIMEOUT = 10*1000;  //设置请求超时10秒钟
        int SO_TIMEOUT = 10*1000;       //设置等待数据超时时间10秒钟
        //HttpConnectionParams.setConnectionTimeout(params, REQUEST_TIMEOUT);
        //HttpConnectionParams.setSoTimeout(params, SO_TIMEOUT);
        params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIMEOUT);
        params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);

        cookieStore = new BasicCookieStore();
        closeableHttpClient = HttpClients.custom()
                .setDefaultHeaders(defaultHeaders).setUserAgent(UA)
                .setDefaultCookieStore(cookieStore).build();
        try {
            outputStream=new FileOutputStream(new File("f://1.log"),true);//append
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void download(FmSong song){
        HttpGet httpGet = new HttpGet(song.getUrl());
        try {
            CloseableHttpResponse httpResponse=closeableHttpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    System.out.println(entity.getContentLength() + "--" + entity.getContentType() + "--" + song.getTitle());
                    String contentType=entity.getContentType().getValue();
                    if(contentType!=null && "audio/mpeg".equals(contentType)){
                        File file=new File("H:\\life story\\songs\\"+song.getTitle()+".mp3");
                        if(!file.exists()){
                            byte []datas=EntityUtils.toByteArray(entity);
                            OutputStream outs=new FileOutputStream(file);
                            IOUtils.write(datas,outs);
                            outs.close();;
                        }
                    }
                    EntityUtils.consume(entity);
                    System.out.println("--------------------------------------");
                }
            }else{
                IOUtils.write(song.getUrl()+"@"+song.getTitle()+"\n",outputStream);
                System.out.println(httpResponse.getStatusLine().getStatusCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            httpGet.completed();
            httpGet.releaseConnection();
        }
    }

    public void closeStream(){
        IOUtils.closeQuietly(outputStream);
    }

}
