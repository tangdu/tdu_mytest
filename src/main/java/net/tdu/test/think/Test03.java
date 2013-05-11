package net.tdu.test.think;

/**
 * 输入：1, 3, 1234 ， 要求输出为 1, 1234, 3
 * @author tangdu
 *
 * @time 2013-3-21 下午10:43:42
 */
public class Test03 extends TestBase<Integer>{

	public static void main(String[] args) {
		Integer arrint []={1,3,1234};
		Test02 t02=new Test02();
		Integer srtint[]=t02.sortBy(arrint);
		t02.print(srtint);
	}
}
