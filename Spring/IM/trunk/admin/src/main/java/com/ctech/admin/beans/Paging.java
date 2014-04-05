package com.ctech.admin.beans;

public class Paging {
	
	private int start;
	private int size;
	private String search;
	private String checkID;
	private int orderCol;
	private String orderBy;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getCheckID() {
		return checkID;
	}
	public void setCheckID(String checkID) {
		this.checkID = checkID;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getOrderCol() {
		return orderCol;
	}
	public void setOrderCol(int orderCol) {
		this.orderCol = orderCol;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}
