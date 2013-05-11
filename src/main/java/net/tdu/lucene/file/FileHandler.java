package net.tdu.lucene.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * 文件处理回掉类
 * @author tangdu
 *
 */
public interface FileHandler {
	void hander(File file) throws FileNotFoundException,IOException;
}
