package com.stock.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {
	//获取数据库连接
	private static DBConnectionManager dbInstance = null;
	private DBConnectionManager()
	{
		
	}
	public static DBConnectionManager getInstance()
	{
		if(dbInstance == null)
		{
			dbInstance = new DBConnectionManager();
		}
		return dbInstance;
	}
	public Connection getConnection()
	{
		try{
			Connection conn = getConnectionByJDBC();  
			if(conn!=null)
			{
				return conn;
			}
		}catch (Exception e)
                                               
	       {

	            e.printStackTrace();
	        }
	       return null;	    
		
	}
	
	
	private Connection getConnectionByJDBC(){
		 Connection conn = null;  
	        try {  
	            //Class.forName("com.mysql.jdbc.Driver");  
	        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
	            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/frame?autoReconnect=true",  
	                  //"root", "root");  
	            /*conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=pgstock",  
	    	                 "sa", "sa");*/
	            conn = DriverManager.getConnection("jdbc:sqlserver://192.168.111.200:1433;databaseName=pgstock",  
                "stock", "stock0423");
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return conn;  
	}
	//关闭单个
	public void close(Object obj) {
		if(obj instanceof ResultSet)
		{
			try {
				if(obj!=null)
				{
					((ResultSet) obj).close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(obj instanceof Statement)
		{
			try {
				if(obj!=null)
				{
					((Statement) obj).close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(obj instanceof Connection)
		{
			try {
				if(obj!=null)
				{
					((Connection) obj).close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//关闭全部
	public void closeAll(ResultSet rs, Statement stmt,
			Connection conn) {
		try {
			if(rs!=null)
			{
				rs.close();
			}
			if(stmt!=null)
			{
				stmt.close();
			}
			if(conn!=null)
			{
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		DBConnectionManager db = DBConnectionManager.getInstance();
		System.out.println("****"+db.getConnection());
	}
}
