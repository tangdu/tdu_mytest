package net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindList {

	public static List<String> getDiffList(List<String> source,List<String> target){
		Map<String,Integer> map=new HashMap<String, Integer>(source.size()+target.size());
		List<String> diffList=new ArrayList<String>();
		for(String n1:source){
			map.put(n1, 1);
		}
		
		for(String n2:target){
			if(!map.containsKey(n2)){
				diffList.add(n2);
			}
		}
		
		return diffList;
	}
	
	public static void main(String[] args) {
		long s=System.currentTimeMillis();
		List<String> source=new ArrayList<String>();
		List<String> target=new ArrayList<String>();
		source.add("1");source.add("2");source.add("3");source.add("4");
		target.add("A");target.add("1");target.add("4");target.add("5");
		List<String> result=getDiffList(source,target);
		for(String a:result){
			System.out.print(a+",");
		}
		System.out.println("--------------->");
		System.out.println("End  Time --"+(System.currentTimeMillis()-s)+" ms");
	}
}
