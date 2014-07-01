package com.stock.index.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestIndexDao {

	IndexDao dao;
	@Before
	public void setUp() throws Exception {
		dao = new IndexDao();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSearchStockNewsByKey() {
		List list = new ArrayList();
		try {
			list = dao.searchStockNewsByKey("深圳上市公司", 0, 100);
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(list.size()>5);
	}

	@Test
	public void testSearchStockNews() {
	}

}
