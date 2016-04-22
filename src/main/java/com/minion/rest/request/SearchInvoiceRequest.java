package com.minion.rest.request;

import java.sql.Date;

import com.minion.service.bean.BillingPeriodCriteria;
import com.minion.service.bean.request.QueryInvoiceRequest;

public class SearchInvoiceRequest {

	private String empId;
	private String password;
	
	private String invoiceId;
	
	private String projectId;
	
	private String poNumber;
	
	private Date startDate;
	
	private Date endDate;
	
	private String statusId;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public QueryInvoiceRequest getServiceRequest() {
		QueryInvoiceRequest request = new QueryInvoiceRequest();
		BillingPeriodCriteria billingPeriod = new BillingPeriodCriteria();
		billingPeriod.setStartDate(getStartDate());
		billingPeriod.setEndDate(getEndDate());

		request.setBillingPeriod(billingPeriod);
		request.setEmpId(getEmpId());
		request.setInvoiceNumber(getInvoiceId());
		request.setPoNumber(getPoNumber());
		request.setProjectId(getProjectId());
		request.setStatusId(getStatusId());
		
		return request;
	}	
}