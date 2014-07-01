package com.stock.dao.impl;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stock.dao.BaseDao;
import com.stock.util.HibernateSessionFactory;

public class BaseDaoImpl implements BaseDao {

	//private static final Logger logger = Logger.getLogger(BaseDaoImpl.class.getName());

	/**
	 * 以下是hibernate 通用Dao实现
	 * @author 谢峰灵
	 * @date 21/03/2011
	 * 
	 */
	
	/**
	 * 删除对象
	 */
	public void delete(Object obj) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.delete(obj);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
		
	}
	/**
	 * 根据id获取对象
	 */
	public Object get(Class clazz, Serializable id) {
		Session s = null;
		try {
			s = HibernateSessionFactory.getSession();
			Object obj = s.get(clazz, id);
			return obj;
		} finally {
			if (s != null)
				s.close();
		}
	}
	/**
	 * 获取List对象
	 */
	public List list(String hql) {
		Session s = null;
		try{
			s = HibernateSessionFactory.getSession();
			Query q = s.createQuery(hql);
			List list = q.list();
			return list;
		}finally
		{
			if (s != null)
				s.close();
		}
	}
	/**
	 * 保存对象到数据库
	 */
	public void save(Object obj) {
		//logger.debug("This is save debug message.");
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.save(obj);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
		
	}
	/**
	 * 更新数据
	 */
	public void update(Object obj) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.update(obj);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
		
	}
	
	public int update(String hql) {
		Session s = null;
		try{
			s = HibernateSessionFactory.getSession();
			s.beginTransaction();
			Query q = s.createQuery(hql);
			int count= q.executeUpdate();
			s.getTransaction().commit();
			return count;
		}
		finally
		{
			if (s != null)
				s.close();
		}
	}

	
	public List query(String hql,Map<String, Object> mapParam) {

		Session s = null;
		try{
		s = HibernateSessionFactory.getSession();
		Query query = s.createQuery(hql);
		
		if (!mapParam.isEmpty()) {
			for (Iterator<String> iter = mapParam.keySet().iterator(); iter.hasNext();) {
				String index = iter.next();
				Object value = mapParam.get(index);
				
					if (value instanceof Integer) {

						query.setInteger(index, (Integer) value);
					} else if (value instanceof String) {
						query.setString(index,(String) value);
					} else if (value instanceof Date) {
						query.setDate(index, (Date) value);
					} else if (value instanceof Timestamp) {
						query.setTimestamp(index,(Timestamp) value);
					}
					else if (value instanceof Long) {

						query.setLong(index, (Long) value);
					}
				
			}
		}
		List list = query.list();
		return list;
		}finally
		{
			if (s != null)
				s.close();
		}
		
	}

	public List query(String hql, List paramList) {
		Session s = null;
		try {
			s = HibernateSessionFactory.getSession();
			Query query = s.createQuery(hql);
			if(paramList.size()>0)
			{
				for(int i=0;i<paramList.size();i++)
				{
					Object value = paramList.get(i);
					if (value instanceof Integer) {

						query.setInteger(i, (Integer) value);
					} else if (value instanceof String) {
						query.setString(i,(String) value);
					} else if (value instanceof Date) {
						query.setDate(i, (Date) value);
					} else if (value instanceof Timestamp) {
						query.setTimestamp(i,(Timestamp) value);
					}
					else if (value instanceof Long) {

						query.setLong(i, (Long) value);
					}
				}
			}
			List list = query.list();
			return list;
		} finally
		{
			if (s != null)
				s.close();
		}
	}

	public void saveOrUpdate(Object obj) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.saveOrUpdate(obj);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
		
	}

	public void persist(Object obj) {

		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			s.persist(obj);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
		
	}

	/**
	 * 分页查询
	 * return list
	 */
	public List query(String hql, Map<String, Object> mapParam,
			int pageNo, int pageSize) {
		Session s = null;
		try{
		s = HibernateSessionFactory.getSession();
		Query query = s.createQuery(hql);
		
		if (!mapParam.isEmpty()) {
			for (Iterator<String> iter = mapParam.keySet().iterator(); iter.hasNext();) {
				String index = iter.next();
				Object value = mapParam.get(index);
				
					if (value instanceof Integer) {

						query.setInteger(index, (Integer) value);
					} else if (value instanceof String) {
						query.setString(index,(String) value);
					} else if (value instanceof Date) {
						query.setDate(index, (Date) value);
					} else if (value instanceof Timestamp) {
						query.setTimestamp(index,(Timestamp) value);
					}
					else if (value instanceof Long) {

						query.setLong(index, (Long) value);
					}
				
			}
		}
		int firstResult=pageSize*(pageNo-1);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List list = query.list();
		return list;
		}finally
		{
			if (s != null)
				s.close();
		}
	}


	//private static HibernateSessionFactory sessionFactory;

	}
