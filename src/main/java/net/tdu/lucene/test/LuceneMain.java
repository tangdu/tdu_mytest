package net.tdu.lucene.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

import net.tdu.lucene.file.FileHandler;
import net.tdu.lucene.file.FileView;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * lucene4.2 测试
 * 
 * @author tangdu
 * 
 */
public class LuceneMain {

	static String INDEXPATH = System.getProperty("user.dir") + "\\index";
	static String DOCPATH = "I:\\个人征信新版模板";
	static String DEFENCODING = "GBK";
	static LuceneMain luceneMain = null;
	static FileView fileView = FileView.getInstance();
	
	public static LuceneMain getInstance() {
		if (luceneMain == null) {
			return luceneMain = new LuceneMain();
		}
		return luceneMain;
	}

	Analyzer analyzer = new IKAnalyzer();// IK分词

	// Analyzer analyzer = new
	// SmartChineseAnalyzer(Version.LUCENE_42);//自带只能中文分词

	/**
	 * 创建索引
	 * 
	 * @param docPath
	 * @throws IOException
	 */
	public void createIndex(String docPath) throws IOException {
		final File index=new File(INDEXPATH);//先删除之前索引
		if(index.exists() && index.canExecute()){
			index.delete();
		}
		
		File docDir = new File(docPath);
		Directory dir = null;
		if (docDir.exists() && docDir.canRead()) {
			long startTime = System.currentTimeMillis();
			dir = FSDirectory.open(index);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_42,
					analyzer);
			IndexWriter iwriter = new IndexWriter(dir, config);
			writerIndex(iwriter, DOCPATH);
			iwriter.close();
			dir.close();
			long endTime = System.currentTimeMillis();
			System.out.println((endTime - startTime) + "ms 索引执行时间");
		}
	}

	/**
	 * 建立索引明细
	 * 
	 * @param indexWriter
	 * @param file
	 * @throws IOException
	 */
	public void writerIndex(final IndexWriter indexWriter, final String filePath)
			throws IOException {
		final FileView f = FileView.getInstance();

		f.execute(filePath, new FileHandler() {
			public void hander(File file) throws FileNotFoundException,
					IOException {
				FileInputStream fis = new FileInputStream(file);
				String filePath = file.getPath();
				Document document = new Document();
				// 创建索引
				document.add(new TextField("name", file.getName(), Store.YES));
				document.add(new TextField("path", filePath, Store.YES));
				document.add(new TextField("content", f.getTextByRegex(fileView
						.readFile(filePath, DEFENCODING)), Store.YES));
				document.add(new LongField("size", file.length(), Store.YES));

				indexWriter.addDocument(document);
				fis.close();
			}
		});

	}

	/**
	 * 查询
	 * 
	 * @param key
	 * @param value
	 * @throws IOException
	 * @throws ParseException
	 * @throws InvalidTokenOffsetsException 
	 */
	public void search(String key, String value) throws IOException,
			ParseException, InvalidTokenOffsetsException {
		long startTime = System.currentTimeMillis();
		Directory directory = FSDirectory.open(new File(INDEXPATH));
		if (directory.listAll().length > 0) {
			IndexReader indexReader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(indexReader);

			Term term = new Term(key, value);
			QueryParser parser = new QueryParser(Version.LUCENE_42, key,
					analyzer);

			 //Query query = parser.parse(value);
			 //Query query = new TermQuery(term);//精确搜索
			 //Query query = new FuzzyQuery(term);//模糊查询
			 //Query query = new WildcardQuery(term);//通配符查询
			 BooleanQuery query = new BooleanQuery();//逻辑组合搜索
			 Term term2 = new Term(key, "王");
			 TermQuery query1 = new TermQuery(term);
			 TermQuery query2 = new TermQuery(term2);
			 query.add(query1, Occur.MUST);
			 query.add(query2, Occur.MUST);

			TopDocs topDocs = searcher.search(query, 1000);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			long endTime = System.currentTimeMillis();

			Highlighter hl = new Highlighter(new QueryScorer(query));

			System.out.println((endTime - startTime) + "ms 查询执行时间");
			System.out.println(indexReader.numDocs() + " 索引内文档数");
			System.out.println(topDocs.totalHits + " 匹配的关键字数");
			System.out.println(indexReader.getCoreCacheKey() + " 索引版本");
			for (ScoreDoc doc : scoreDocs) {
				Document document = searcher.doc(doc.doc);
				TokenStream ts = analyzer.tokenStream(key, new StringReader(document.get(key)));
				String frament = hl.getBestFragment(ts,
						document.get(key));
				System.out.println(frament);
			}
			indexReader.close();
			directory.close();
		}
	}

	@Test
	public void createIndexTest() throws Exception {
		createIndex(DOCPATH);
	}

	@Test
	public void queryTest() throws Exception {
		search("content", "上海");
	}
	
	@Test
	public void testAnalyzer(){
		//Analyzer analyzer = new  SmartChineseAnalyzer(Version.LUCENE_42);//自带中文分词 
		Analyzer analyzer = new IKAnalyzer(true);// IK分词
		FileView f=FileView.getInstance();
		String str=f.getTextByRegex(f.readFile(
				"I:\\个人征信新版模板\\2012090500001122007863.html", "gb2312"));
		TokenStream stream;
		try {
			stream = analyzer.tokenStream("name", new StringReader(str));
			while(stream.incrementToken()){
				CharTermAttribute attribute = stream.getAttribute(CharTermAttribute.class);  
				System.out.println(attribute);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
