package com.minion.loader.excel;

import java.sql.Date;

public class KronosBean {
	
	private String name;
	private String id;
	private Date date;
	private String account;
	private String xfr;
	private String organization;
	private String payCode;
	private String override;
	private String money;
	private double expectedHours;
	private double loggedHours;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getXfr() {
		return xfr;
	}
	public void setXfr(String xfr) {
		this.xfr = xfr;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getPayCode() {
		return payCode;
	}
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	public String getOverride() {
		return override;
	}
	public void setOverride(String override) {
		this.override = override;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public double getExpectedHours() {
		return expectedHours;
	}
	public void setExpectedHours(double expectedHours) {
		this.expectedHours = expectedHours;
	}
	public double getLoggedHours() {
		return loggedHours;
	}
	public void setLoggedHours(double loggedHours) {
		this.loggedHours = loggedHours;
	}
	
	@Override
	public String toString(){
		StringBuilder objString = new StringBuilder();
		objString.append(this.name).append("|").append(id).append("|").append(date).append("|")
		.append(account).append("|").append(xfr).append("|").append(organization).append("|")
		.append(payCode).append("|").append(expectedHours).append("|").append(loggedHours);
		return objString.toString();
	}

}
