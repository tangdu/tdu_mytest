
package net.tdu.test.think;

import java.util.Arrays;

import org.junit.Test;

/**
 * 给定一个数组{c,d,a,b,e,g,f,k,c,b,a}, 要求找出包含下列子串的最小字符串{a,b,c}
 * @author tangdu
 *
 * @time 2013-3-22 上午12:22:33
 */
public class Test12 {

	
	public char [] minStr(char [] chrs){
		
		
		//distinct
		StringBuilder builder=new StringBuilder();
		for (int i=0;i<chrs.length;i++){
			if(builder.indexOf(String.valueOf(chrs[i]))<0){
				builder.append(chrs[i]);
			}
		}
		
		char cp[]=builder.toString().toCharArray();
		
		Arrays.sort(cp);
		
		return cp;
	}
	
	void print(char [] chrs){
		for(int i=0;i<chrs.length;i++){
			System.out.println(chrs[i]);
		}
	}
	
	@Test
	public void runt(){
		char cp[]=minStr(new char []{'c','d','a','b','e','g','f','k','c','b','a'});
		//得到最小3个字符串，还是最小连续字符串?
		print(cp);
	}
}
