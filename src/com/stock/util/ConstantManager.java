package com.stock.util;

import org.apache.lucene.util.Version;


public class ConstantManager {
	public final static String INDEX_DIR="D:\\StockIndex";
	
	public final static String SOURCE_FILE_DIR="";
	public static final String STR_TITLE_FIELD = "title";
	public static final String STR_STOCKCONTENT_FIELD="stockContent";
	public static final String[] STR_TITLE_STOCKCONTENT_FIELDS = {"title","stockContent"};
	public static final Version CURRENT_VERSION=Version.LUCENE_33;
	public static final int MAX_HITS_NUM=10000000;
}
