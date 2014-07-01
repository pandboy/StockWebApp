package com.stock.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {
	public void save(Object obj);
	
	public void update(Object obj);
	public int update(String hql);
	
	public void delete(Object obj);
	
	public Object get(Class clazz,java.io.Serializable id);
	
	public List list(String hql);
	
	public List query(String hql,Map<String, Object> mapParam);
	
	public List query(String hql,List paramList);
	
	public List query(String hql,Map<String,Object> mapParam,int pageNo, int pageSize);
	
	public void saveOrUpdate(Object obj);
	
	/**
	 * ³Ö¾Ã»¯persist
	 */
	public void persist(Object obj);
	
	
}
