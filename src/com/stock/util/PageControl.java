package com.stock.util;

import java.util.List;

public class PageControl {

	private int curPage; // 当前页
	private int maxPage; // 一共有多少页
	private long totalHits; // 总记录
	private int rowsPerPage = 10; // 每页几条数据
	private List<?> data; // 每页数据

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getMaxPage() {
		if (this.totalHits % this.rowsPerPage == 0) {
			this.maxPage = (int) (this.totalHits / this.rowsPerPage);
		} else {
			this.maxPage = (int) (this.totalHits / this.rowsPerPage + 1);
		}
		return this.maxPage;
	}

	public void setMaxPage(int maxPage) {
		if (this.totalHits % this.rowsPerPage == 0) {
			this.maxPage = (int) (this.totalHits / this.rowsPerPage);
		} else {
			this.maxPage = (int) (this.totalHits / this.rowsPerPage + 1);
		}
	}

	public long getTotalHits() {
		return totalHits;
	}

	public void setTotalHits(long totalHits) {
		this.totalHits = totalHits;
	}

	public int getRowsPerPage() {
		return 10;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public void countMaxPage() {

	}
}
