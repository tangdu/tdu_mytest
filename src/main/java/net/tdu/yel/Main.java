package net.tdu.yel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: tangdu
 * Date: 13-12-28
 * Time: 下午5:39
 * To change this template use File ||
 */
public class Main {

    static String index = "http://www.avtt5.com/katongdongman/";
    static String dir = "H:\\life story\\mv\\dddd\\";
    static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static void main(String[] args) {
        try {
            HttpGet httpGet = new HttpGet(index);
            HttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String content = EntityUtils.toString(entity, "gb2312");
                    parseIndex(content);
                    EntityUtils.consume(entity);
                }
            }
            httpGet.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void parseIndex(String content) {
        Document document = org.jsoup.Jsoup.parse(content);
        Elements elements = document.select("div[id=container] div[class=layout_newlistpic] div[class=newslist] ul:eq(1) li");

        for (Element e : elements) {
            String href = "http://www.avtt5.com" + e.select("a[href]").attr("href");
            String title = e.text();
            if (StringUtils.isNotBlank(href)) {
                File dirFile = new File(dir + title);
                try {
                    HttpGet httpGet = new HttpGet(href);
                    HttpResponse response = httpclient.execute(httpGet);
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            String content3 = EntityUtils.toString(entity, "gb2312");
                            parsePage(content3, dirFile.getPath());
                            EntityUtils.consume(entity);
                        }
                    }
                    httpGet.releaseConnection();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }


    public static void parsePage(String content, String dirFile) {
        Document document = org.jsoup.Jsoup.parse(content);
        Elements elements = document.select("div[id=container] div[class=cntent_p_n] div[id=text] img");

        int i = 1;
        for (Element e : elements) {
            String path = e.select("img[src]").attr("src");
            System.out.println(path);
            down(dirFile, i, path);
            i++;
        }
    }

    /**
     * 下载
     *
     * @param dirFile
     * @param i
     * @param imgurl
     */
    public static void down(String dirFile, int i, String imgurl) {
        try {
            HttpGet httpGet = new HttpGet(imgurl);
            HttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println(dirFile);
                    File file = new File(dirFile + "\\" + i + ".jpg");
                    FileOutputStream fileOutputStream = FileUtils
                            .openOutputStream(file);
                    InputStream input = entity.getContent();
                    try {
                        IOUtils.copy(input, fileOutputStream);
                    } finally {
                        IOUtils.closeQuietly(fileOutputStream);
                    }
                    EntityUtils.consume(entity);
                }
            }
            httpGet.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
