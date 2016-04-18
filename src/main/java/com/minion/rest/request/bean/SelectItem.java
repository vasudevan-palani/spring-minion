package com.minion.rest.request.bean;

public class SelectItem {
	String name;
	String filter;
	
	public SelectItem(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	
}
