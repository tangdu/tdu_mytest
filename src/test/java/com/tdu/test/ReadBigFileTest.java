package com.tdu.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

public class ReadBigFileTest {

	public static void main(String[] args) {
		//buffRead();
		buffCharRead();
	}
	
	public static void utils(){
		try {
			long all1=System.currentTimeMillis();
			for(int i=0;i<20;i++){
				long s1=System.currentTimeMillis();
				FileUtils.readFileToString(new File("C:\\Users\\tangdu\\Downloads\\2089.txt"),"UTF-8");
				long s2=System.currentTimeMillis();
				System.out.println(s2-s1);
			}
			long all2=System.currentTimeMillis();
			System.out.println(all2-all1+"--------ALL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void buffCharRead(){
		try {
			long all1=System.currentTimeMillis();
			for(int i=0;i<1;i++){
				long s1=System.currentTimeMillis();
				FileReader inputStream=new FileReader(new File("C:\\Users\\tangdu\\Downloads\\2089.txt"));
				BufferedReader bufferedInputStream=new BufferedReader(inputStream);
				char data[]=new char[1024];
				while((bufferedInputStream.read(data))!=-1){
					System.out.println(data);
				}
				long s2=System.currentTimeMillis();
				System.out.println(s2-s1);
			}
			long all2=System.currentTimeMillis();
			System.out.println(all2-all1+"--------ALL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void buffRead(){
		try {
			long all1=System.currentTimeMillis();
			for(int i=0;i<1;i++){
				long s1=System.currentTimeMillis();
				InputStream inputStream=new FileInputStream(new File("C:\\Users\\tangdu\\Downloads\\2089.txt"));
				BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
				byte data[]=new byte[1024];
				while((bufferedInputStream.read(data))!=-1){
					System.out.println(String.valueOf(data));
				}
				long s2=System.currentTimeMillis();
				System.out.println(s2-s1);
			}
			long all2=System.currentTimeMillis();
			System.out.println(all2-all1+"--------ALL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
