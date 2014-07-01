package com.stock.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfigSingleton {

	
	private String indexDir;
	private String fileDir;
	private Properties prop = null;
	private static String SOURCE_PROPERTY_FILE="AppConfig.properties";
	private static class SingletonHoler
	{
		private static AppConfigSingleton instance = new AppConfigSingleton();
	}
	
	private AppConfigSingleton()
	{
		readConfig();
	}
	public static AppConfigSingleton getInstance()
	{
		return SingletonHoler.instance;
	}
	
	public String getIndexDir() {
		return indexDir;
	}
	public void setIndexDir(String indexDir) {
		this.indexDir = indexDir;
	}
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	private void readConfig()
	{
		InputStream in = null;;
		
		try {
			prop = new Properties();
			System.out.println(SOURCE_PROPERTY_FILE);
			in = AppConfigSingleton.class.getClassLoader().getResourceAsStream(SOURCE_PROPERTY_FILE);
			System.out.println(in == null);
			prop.load(in);
			this.indexDir = prop.getProperty("indexstockdectory");
			this.fileDir = prop.getProperty("sourcefiledectory");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(in != null)
				{
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		AppConfigSingleton cfg = AppConfigSingleton.getInstance();
		System.out.println(cfg==null);
		System.out.println(cfg.getFileDir());
		System.out.println(cfg.getIndexDir());
	}
}
