package com.stock.index.dao;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopDocsCollector;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;

import com.stock.entity.StockNews;
import com.stock.util.ConstantManager;
import com.stock.util.LuceneUtils;
import com.stock.vo.StockNewsVo;

public class IndexDao {
	
	private File file = null;
	private static final String STR_FIELD = "title";
	private static final String FIELD_CONTENT="stockContent";
	private static final String FIELD_TITLE="title";
	
	private String prefixHTML = "<b><font color='red'>";
	private String suffixHTML = "</font></b>";
	
	private Analyzer analyzer = new IKAnalyzer();
	@Deprecated
	public void search() throws Exception
	{
		Date startTime = new Date();
		String strQueries  = "股票";
		//String[] fields = {"filename","content"};

		//String[] fields = {"fileName","fileContent"};
		String field = "content";
		//单个Field的
		QueryParser parser = new QueryParser(ConstantManager.CURRENT_VERSION,field,LuceneUtils.getInstance().getAnalyzer());
		//多个Field
		//QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_33,fields,LuceneUtils.getInstance().getAnalyzer());  //多字段
		
		Query query = parser.parse(strQueries);
		
		//IndexReader indexReader = IndexReader.open(dir);
		//IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtils.getInstance().getIndexDir());
		TopDocs hits = indexSearcher.search(query, 100000);
		Date endTime = new Date();
		System.out.println("找到了"+hits.totalHits+"条记录,共花了"+(endTime.getTime()-startTime.getTime())+"毫秒");
		indexSearcher.close();
		
		
	}
	
	public List searchStockNewsByKey(String searchKey,int start,int end) throws ParseException, CorruptIndexException, IOException
	{
		List list = new ArrayList();
		//QueryParser parser = new QueryParser(ConstantManager.CURRENT_VERSION,STR_FIELD,LuceneUtils.getInstance().getAnalyzer());
		//QueryParser parser = new MultiFieldQueryParser(ConstantManager.CURRENT_VERSION,STR_FIELDS,LuceneUtils.getInstance().getAnalyzer());  //多字段
		//Query query = parser.parse(searchKey);
		SortField[] sortField = new SortField[] {new SortField("pubDate", SortField.DOC,true)};
		Sort sort =  new Sort(sortField);
		Query query = IKQueryParser.parseMultiField(ConstantManager.STR_TITLE_STOCKCONTENT_FIELDS, searchKey);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtils.getInstance().getIndexDir());
		//TopDocsCollector topDocsCollector = TopScoreDocCollector.create(start+end, false);
		 TopFieldCollector topDocsCollector = TopFieldCollector.create(sort,start+end, true,false,true,false);
		indexSearcher.search(query, topDocsCollector);
		TopDocs hits = topDocsCollector.topDocs(start, end);
		//ScoreDoc[] docs = hits.scoreDocs;
		StockNewsVo stockNews = null;
		
		Formatter formatter = new SimpleHTMLFormatter(prefixHTML, suffixHTML);
		//Formatter formatter = new SimpleHTMLFormatter("<read>", "</read>");
		Highlighter hightlighter = new Highlighter(formatter,new QueryScorer(query) );
		//hightlighter.setTextFragmenter(new SimpleFragmenter(1000));
		String highlightText = null;
		for(ScoreDoc scoreDoc:hits.scoreDocs)
		{
			
			String strScore = Float.toString(scoreDoc.score);
			int docSn = scoreDoc.doc;
			stockNews = new StockNewsVo();
			Document doc = indexSearcher.doc(docSn);
			String title = doc.get("title");
			String stockContent = doc.get("stockContent");
			String cacheContent = doc.get("cacheContent");
			String url = doc.get("srcUrl");
		//	String strBoost =  doc.getBoost()+"";
			String strPubDate = doc.get("pubDate");
			String titleTmp = null;
			String stockContentTemp = stockContent;
			try {
				highlightText = hightlighter.getBestFragment(LuceneUtils.getInstance().getAnalyzer(),"stockContent",stockContent);
				titleTmp = hightlighter.getBestFragment(LuceneUtils.getInstance().getAnalyzer(), "title", title);
				
				//System.out.println("标题："+title);
			} catch (InvalidTokenOffsetsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			if(stockContent.length()>100)
			{
				stockContentTemp = stockContentTemp.substring(0, 100);
				stockContent = stockContent.substring(0, 100);
			}
			try {
				stockContentTemp = hightlighter.getBestFragment(LuceneUtils.getInstance().getAnalyzer(), FIELD_CONTENT, stockContentTemp);
				title2 = hightlighter.getBestFragment(LuceneUtils.getInstance().getAnalyzer(), FIELD_TITLE, title2);
				stockNews.setTitle(title2!=null?title2:title);
			} catch (InvalidTokenOffsetsException e) {
				System.out.println("设置高亮发生错误");
				e.printStackTrace();
			}
			*/
			//stockNews.setStockContent(stockContentTemp!=null?stockContentTemp:stockContent);
			stockNews.setTitle(titleTmp!=null?titleTmp:title);
			stockNews.setStockContent(highlightText);
			stockNews.setCacheContent(cacheContent);
			stockNews.setUrl(url);
			stockNews.setStrScore(strScore);
			stockNews.setStrPubDate(strPubDate);
			list.add(stockNews);
		}
		return list;
	}
	
	public int coutSearchStockNews(String searchKey) throws ParseException, CorruptIndexException, IOException
	{
		//QueryParser parser = new QueryParser(ConstantManager.CURRENT_VERSION,STR_FIELD,LuceneUtils.getInstance().getAnalyzer());
		/*QueryParser parser = new MultiFieldQueryParser(ConstantManager.CURRENT_VERSION,STR_FIELDS,LuceneUtils.getInstance().getAnalyzer());  //多字段
		Query query = parser.parse(searchKey);*/
		Query query = IKQueryParser.parseMultiField(ConstantManager.STR_TITLE_STOCKCONTENT_FIELDS, searchKey);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtils.getInstance().getIndexDir());
		TopDocs hits = indexSearcher.search(query, ConstantManager.MAX_HITS_NUM);
		ScoreDoc[] docs = hits.scoreDocs;
		indexSearcher.close();
		return docs.length;
	}
	
	public List searchStockNews(String searchKey) throws ParseException, CorruptIndexException, IOException
	{
		List list = new ArrayList();
		QueryParser parser = new QueryParser(Version.LUCENE_33,STR_FIELD,LuceneUtils.getInstance().getAnalyzer());
		Query query = parser.parse(searchKey);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtils.getInstance().getIndexDir());
		TopDocs hits = indexSearcher.search(query, 100000);
		ScoreDoc[] docs = hits.scoreDocs;
		Map map  = null;
		StockNews stockNews = null;
		for(ScoreDoc scoreDoc:hits.scoreDocs)
		{
			int docSn = scoreDoc.doc;
			stockNews = new StockNews();
			Document doc = indexSearcher.doc(docSn);
			String title = doc.get("title");
			String stockContent = doc.get("stockContent");
			String cacheContent = doc.get("cacheContent");
			stockNews.setTitle(title);
			//System.out.println("标题：---》"+title);
			if(stockContent.length()>50)
			{
				stockContent = stockContent.substring(0, 50);
			}
			//System.out.println("文章内容：---》"+stockContent);
			stockNews.setStockContent(stockContent);
			stockNews.setCacheContent(cacheContent);
			list.add(stockNews);
		}
		
		indexSearcher.close();
		return list;
	}
	
}
