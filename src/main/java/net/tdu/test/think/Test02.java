package net.tdu.test.think;

import java.util.Arrays;

import org.junit.Test;

/**
 * 2. Giving a list of integers: 1,2,12,8,25. Write a program that sorts them
 * based on digits. The first digit has the highest priority and so forth.
 * 1,12,2,25,8
 * 
 * @author tangdu
 * 
 * @time 2013-3-21 下午10:11:57
 */
public class Test02 extends TestBase<Integer> {
	
	public Integer [] sortBy(Integer arrint[]) {
		// get max len
		Arrays.sort(arrint);
		int len = String.valueOf(arrint[arrint.length - 1]).length();

		String zeroStr = "";
		;
		for (int i = 0; i < len; i++) {
			zeroStr += "0";
		}

		for (int i = 0; i < arrint.length; i++) {
			for (int j = 0; j < arrint.length; j++) {
				int temp;

				//右补0比较
				int a_t = Integer
						.valueOf(arrint[i]
								+ zeroStr.substring(String.valueOf(arrint[i])
										.length()));
				int b_t = Integer
						.valueOf(arrint[j]
								+ zeroStr.substring(String.valueOf(arrint[j])
										.length()));

				if (a_t < b_t) {
					temp = arrint[j];
					arrint[j] = arrint[i];
					arrint[i] = temp;
				}
			}
		}
		
		return arrint;
	}


	@Test
	public void run() {
		Integer []  arrint=sortBy(new Integer []{4,3,1,46,8});
		print(arrint);
	}
}
