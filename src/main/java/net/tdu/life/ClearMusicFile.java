package net.tdu.life;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * 清除music下文件
 * 
 * 规则： 1.只有歌词文件 2.重复文件(lrc,mp3）
 * 
 * @author tangdu
 * 
 * @time 2013-3-26 上午8:57:39
 */
public class ClearMusicFile {

	private static final String SUFFIX_LRC = "lrc";
	private static final String SUFFIX_MP3 = "mp3";
	private static final String FILE_PATH = "H:\\life story\\music";

	private static File[] files = null;
	private static Set<String> mp3files=new HashSet<String>();
	
	
	static {
		File file = new File(FILE_PATH);
		if (file.isDirectory()) {
			files = file.listFiles();
		}
	}

	public void clearLRC() {
		if(files!=null){
			int distinctCnt=0;
			for(File file :files){
				
				for(String filename: mp3files){
					if(file.getName().startsWith(filename)){
						distinctCnt++;
						if(distinctCnt>1){//存在重复mp3 or lrc
							if(canExecute(file)){
								file.delete();
							}
						}
					}
				}
			}
		}
	}
	
	private boolean canExecute(File file){
		if(file!=null){
			if(file.exists() && file.canExecute()){
				return true;
			}
		}
		return false;
	}

	public void getMp3Files() {
		if(files!=null){
			for(File file :files){
				if(file.getName().endsWith(SUFFIX_MP3)){
					mp3files.add(file.getName());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ClearMusicFile musicFile=new ClearMusicFile();
		musicFile.getMp3Files();
		musicFile.clearLRC();
	}
}
