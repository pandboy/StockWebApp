package com.stock.service;

import java.io.IOException;
import java.util.Map;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;

import com.stock.util.PageControl;

public interface SearchStock {
	public PageControl searchStock(String jumpPage,String searchkeyword, String searchtype, PageControl pageControl ) throws NumberFormatException, CorruptIndexException, ParseException, IOException;
}
