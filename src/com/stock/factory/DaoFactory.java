package com.stock.factory;

import com.stock.index.dao.IndexDao;
/**
 * 简单工厂，实例化indexDao对象
 * @author killerboy
 *
 */
public class DaoFactory {
	
	public static IndexDao createIndexDao(int condition)
	{
		IndexDao indexDao = null;
		if(condition == 1)
		{
			indexDao = new IndexDao();
		}
		return indexDao;
	}
}
