package net.csv;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;


public class CsvParse {
	public static void main(String[] args) {
		try {
			List<String> conts=FileUtils.readLines(new File("/Users/tangdu/Desktop/rms_org_info.txt"),"gbk");
			File newFile=new File("/Users/tangdu/Desktop/rms_org_info.sql");
			if(newFile.exists()){
				newFile.delete();
			}
			for(String s :conts){
				String cc[]=s.split("[#]");
				StringBuffer sb=new StringBuffer("insert into T_ORG_INFO values(");
				if(cc.length>0){
					System.out.println(cc[1]);
					sb.append("'").append(cc[0]).append("',");
					sb.append("'").append(cc[1]).append("',");
					sb.append("'").append(cc[2]).append("',");
					sb.append("'").append(cc[3]).append("',");
					sb.append("'").append(cc[4]).append("',");
					sb.append("'").append(cc[5]).append("'");
				}
				sb.append(");").append("\n");
				FileUtils.write(newFile, sb,"utf-8", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
