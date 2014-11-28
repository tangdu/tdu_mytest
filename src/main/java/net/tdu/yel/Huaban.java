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
 * Created by tangdu on 14-3-7.
 */
public class Huaban {
    static String dir = "H:\\life story\\mv\\imgs\\";
    static CloseableHttpClient httpclient = HttpClients.createDefault();
    public static void main(String [] args){
        try {
            for(int i=0;i<150;i++){
                String href="http://cued.xunlei.com/demos/publ/img/P_"+String.format("%03d",i)+".jpg";
                File file = new File(dir + "P_"+String.format("%03d",i)+".jpg");

                try {
                    HttpGet httpGet = new HttpGet(href);
                    HttpResponse response = httpclient.execute(httpGet);
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            System.out.println(file.getPath());
                            FileOutputStream fileOutputStream = FileUtils
                                    .openOutputStream(file);
                            InputStream input = entity.getContent();
                            try {
                                IOUtils.copy(input, fileOutputStream);
                            } finally {
                                IOUtils.closeQuietly(fileOutputStream);
                            }
                        }
                    }
                    httpGet.releaseConnection();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
