package com.minion.service.bean.request;

import java.sql.Date;

import com.minion.service.bean.BillingPeriodCriteria;

public class QueryInvoiceRequest {

	private String empId;
	private String invoiceNumber;
	private Date invoiceDate;
	private String poNumber;
	private String projectId;
	private String statusId;

	private BillingPeriodCriteria billingPeriod;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public BillingPeriodCriteria getBillingPeriod() {
		return billingPeriod;
	}

	public void setBillingPeriod(BillingPeriodCriteria billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

}