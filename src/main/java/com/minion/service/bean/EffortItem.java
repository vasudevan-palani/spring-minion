package com.minion.service.bean;

import java.sql.Date;

public class EffortItem {
	private Date date;
	private float effort;

	private String dateStr;
	

	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getEffort() {
		return effort;
	}
	public void setEffort(float effort) {
		this.effort = effort;
	}
	
	
}
