package com.minion.service.request;

public class InvoiceUser {
	private String invoiceNumer;
	private String empId;
	private Float hours;
	private Integer billingRate;
	private Float total;
	
	private Integer invoiceId;
	private Integer userId;
	
	public String getInvoiceNumer() {
		return invoiceNumer;
	}
	public void setInvoiceNumer(String invoiceNumer) {
		this.invoiceNumer = invoiceNumer;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public Float getHours() {
		return hours;
	}
	public void setHours(Float hours) {
		this.hours = hours;
	}
	public Integer getBillingRate() {
		return billingRate;
	}
	public void setBillingRate(Integer billingRate) {
		this.billingRate = billingRate;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Integer getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
