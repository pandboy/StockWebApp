package com.stock.service.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;

import com.stock.index.dao.IndexDao;
import com.stock.service.SearchStock;
import com.stock.util.PageControl;

public class SearchStockImpl implements SearchStock {

	public PageControl searchStock(String jumpPage, String searchkeyword,
			String searchtype, PageControl pageControl) throws NumberFormatException, CorruptIndexException, ParseException, IOException {
		IndexDao indexDao = new IndexDao();
		if(jumpPage == null)
		{
			jumpPage = "1";
		}
		if(searchtype == null || "".equals(searchtype))
		{
			searchtype = 0+"";
		}
		pageControl.setTotalHits(indexDao.coutSearchStockNews(searchkeyword));
		pageControl.setData(indexDao.searchStockNewsByKey(searchkeyword,(Integer.valueOf(jumpPage)-1)*pageControl.getRowsPerPage(),(Integer.valueOf(jumpPage)-1)*10+pageControl.getRowsPerPage()));
		pageControl.setCurPage(Integer.valueOf(jumpPage));
		return pageControl;
	}

}
