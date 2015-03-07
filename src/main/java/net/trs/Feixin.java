package net.trs;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Feixin {

	public static void main(String[] args) {
		CloseableHttpClient client=HttpClients.createDefault();
		HttpGet request=new HttpGet("http://download.fetion-portal.com/FetionNew_2014_September.exe");
		try {
			HttpResponse httpResponse=client.execute(request);
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				
			}else{
				System.out.println(httpResponse.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
