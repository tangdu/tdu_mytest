package net.tdu.tmp;

import java.io.File;

/**
 * 删除目录下所有.svn文件夹
 * 
 * @author tangdu
 * 
 * @time 2013-4-8 下午3:29:17
 */
public class DelSVnFile {
	private static final String path = "G:\\work\\项目资料\\20130408hzor\\hzb_or";

	public static void main(String[] args) {
		File file = new File(path);
		new DelSVnFile().query(file);
	}

	private void delFile(File file) {
		if (file.isDirectory()) {
			for (File file_ : file.listFiles()) {
				delFile(file_);
			}
			file.delete();
		} else {
			file.delete();
		}

	}

	private void query(File file) {
		if (file.isDirectory()) {
			if (file.getName().endsWith(".svn") && file.isHidden()) {
				delFile(file);
			} else {
				for (File file_ : file.listFiles()) {
					query(file_);
				}
			}
		}
	}
}
