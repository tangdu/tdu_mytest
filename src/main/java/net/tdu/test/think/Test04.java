package net.tdu.test.think;

import org.junit.Test;

/**
 * 有任意字符串a b c dd e"adf   sdf   sdfa  abc"d d c f
 * 。如果字符串中有连续的空格，请去除保留一个空格。但是在“”内的字符串不做处理，保留原样
 * 
 * @author tangdu
 * 
 * @time 2013-3-21 下午11:28:02
 */
public class Test04 extends TestBase<Character> {

	public void replaceStr(char[] str) {
		for (int i = 0; i < str.length; i++) {
			for (int j = i+1; j < str.length; j++) {

			}
		}
	}

	@Test
	public void run() {
		String str = "a b c dd e'adf   sdf   sdfa  abc'd d c f";
		replaceStr(str.toCharArray());
	}
}
