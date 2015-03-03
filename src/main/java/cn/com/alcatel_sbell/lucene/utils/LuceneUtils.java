package cn.com.alcatel_sbell.lucene.utils;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;


public class LuceneUtils {
	private static Directory directory;
	private static Analyzer analyzer;
	private static IndexWriter indexWriter;
	public static String indexPath;

	static {
		try {
			indexPath = LuceneUtils.class.getResource("/").getPath();
			directory = FSDirectory.open(new File(indexPath+"../../index"));
			analyzer = new IKAnalyzer();
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					Version.LATEST, analyzer);
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			// 虚拟机出时关闭
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					System.out.println("关闭IndexWriter...");
					try {
						indexWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取IndexWriter
	public static IndexWriter getIndexWriter() throws IOException {
		return indexWriter;
	}

	// 获取IndexSearcher
	public static IndexSearcher getIndexSearcher() throws Exception {
		return new IndexSearcher(DirectoryReader.open(directory));
	}
	public static Document getDocumentByAssignId(String idFilesName,String idValue) throws Exception{
		Query query = new TermQuery(new Term(idFilesName, idValue));
		IndexSearcher indexSearcher=LuceneUtils.getIndexSearcher();
		TopDocs search = indexSearcher.search(query, 1);
		ScoreDoc scoreDoc = search.scoreDocs[0];
		Document document = indexSearcher.doc(scoreDoc.doc);
		return document;
		
		
		
		
		
	}

	public static void deleteAllDocument() throws Exception {
		Query query = new MatchAllDocsQuery();
		indexWriter.deleteDocuments(query);
		indexWriter.commit();
	}
}
