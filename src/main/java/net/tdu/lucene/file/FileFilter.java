package net.tdu.lucene.file;

import java.io.File;

/**
 * 文件过滤接口
 * @author tangdu
 *
 */
public interface FileFilter {

	public boolean handler(File file);
}
