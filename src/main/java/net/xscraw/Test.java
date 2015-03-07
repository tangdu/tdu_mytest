package net.xscraw;

public class Test {

	public static void main(String[] args) {
		
		String c=HttpClientUtils.getDataStr("http://www.jdxs.net/files/article/html/109/109422/index.html");
		System.out.println(c);
	}
}
