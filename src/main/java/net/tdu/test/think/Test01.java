package net.tdu.test.think;

import java.io.File;

import org.junit.Test;

/**
 * 写一个程序能把一个文件夹下包括子目录内的所有文件的文件名打印出来
 * 
 * @author tangdu
 * 
 * @time 2013-3-21 下午10:07:32
 */
public class Test01 {
	private static final StringBuilder BUILDER = new StringBuilder();

	public void getFileName(File file, FileFilter filter) {
		if (file.isDirectory()) {
			for (File file_ : file.listFiles()) {
				getFileName(file_, filter);
			}
		} else {
			if (filter.handler(file)) {
				BUILDER.append(file.getName())
						.append("\n");
			}
		}
	}

	@Test
	public void run() {
		getFileName(new File("G:\\workspace\\.metadata"),new FileFilter() {
			public boolean handler(File file) {
				if(file.getName().endsWith("zip"))
					return true;
				return false;
			}
		});
		System.out.println(BUILDER.toString());
	}
}

interface FileFilter {
	boolean handler(File file);
}