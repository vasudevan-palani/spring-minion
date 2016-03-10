package com.minion.service.request;

import java.sql.Date;
import java.util.List;

public class Invoice {
	private Integer id;
	private Date startDate;
	private Date endDate;
	private Date invoiceDate;
	private String invoiceFile;
	private Integer poId;
	private String poNumber;
	private Integer projectId;
	private String projectNumber;
	private String status;
	private Float total;
	
	private List<InvoiceUser> invoiceUsers;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getInvoiceFile() {
		return invoiceFile;
	}
	public void setInvoiceFile(String invoiceFile) {
		this.invoiceFile = invoiceFile;
	}
	public Integer getPoId() {
		return poId;
	}
	public void setPoId(Integer poId) {
		this.poId = poId;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public List<InvoiceUser> getInvoiceUsers() {
		return invoiceUsers;
	}
	public void setInvoiceUsers(List<InvoiceUser> invoiceUsers) {
		this.invoiceUsers = invoiceUsers;
	}
	
	
	
}
