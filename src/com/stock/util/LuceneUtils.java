package com.stock.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldSelectorResult;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneUtils {
	
	private static LuceneUtils instance;
	private Analyzer analyzer;
	private Directory indexDir;
	private LuceneUtils()
	{
		analyzer =  new IKAnalyzer() ;
		File file = new File(AppConfigSingleton.getInstance().getIndexDir());
		try {
			indexDir = FSDirectory.open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static LuceneUtils getInstance()
	{
		if(instance==null)
		{
			instance = new LuceneUtils();
		}
		return instance;
	}
	
	
	
	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}


	public Directory getIndexDir() {
		return indexDir;
	}

	public void setIndexDir(Directory indexDir) {
		this.indexDir = indexDir;
	}
	
}
