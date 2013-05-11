package net.tdu.lucene.expand;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Lucene4.2 工具类
 * 
 * @author tangdu
 * 
 * @time 2013-3-31 下午9:28:54
 */
public class LuceneUtils {

	private static final String _INDEX_PATH = "D:" + File.separator + "index"
			+ File.separator;
	private static final Analyzer ANALYZER = new IKAnalyzer();// IK分词

	public static void add(String filepath) {
		IndexWriter iwriter=getWriter();
		if(iwriter!=null){
			writerIndex(iwriter, filepath);
		}
		try {
			iwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到索引写
	 * 
	 * @return
	 */
	protected static IndexWriter getWriter() {
		Directory directory = null;
		try {
			directory = FSDirectory.open(new File(_INDEX_PATH));
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_43,
					ANALYZER);
			return new IndexWriter(directory, config);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (directory != null) {
					directory.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 得到索引读
	 * @return
	 */
	protected static IndexSearcher getReader() {
		Directory directory = null;
		try {
			directory = FSDirectory.open(new File(_INDEX_PATH));
			if (directory.listAll().length > 0) {
				IndexReader indexReader = DirectoryReader.open(directory);
				IndexSearcher searcher = new IndexSearcher(indexReader);
				return searcher;
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (directory != null) {
					directory.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static void writerIndex(final IndexWriter indexWriter,
			final String filePath) {

	}
}
