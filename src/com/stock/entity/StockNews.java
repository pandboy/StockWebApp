package com.stock.entity;

import java.io.Serializable;
import java.util.Date;

public class StockNews implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private String author;
	private Date pubDate;
	private String url;
	private String stockContent;
	private String stockFrom;
	private String cacheContent;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStockContent() {
		return stockContent;
	}
	public void setStockContent(String stockContent) {
		this.stockContent = stockContent;
	}
	public String getStockFrom() {
		return stockFrom;
	}
	public void setStockFrom(String stockFrom) {
		this.stockFrom = stockFrom;
	}
	public String getCacheContent() {
		return cacheContent;
	}
	public void setCacheContent(String cacheContent) {
		this.cacheContent = cacheContent;
	}
	
}
