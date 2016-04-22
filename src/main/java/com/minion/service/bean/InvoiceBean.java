package com.minion.service.bean;

import java.sql.Date;
import java.util.List;

import com.minion.model.view.InvoiceSearch;

public class InvoiceBean {

	private Date startDate;
	
	private Date endDate;

	private Integer projectId;
	
	private Integer poId;
	
	private String poNumber;

	private String invoiceId;
	
	private Float total;
	
	private Integer statusId;

	private String invoiceFile;

	private Date invoiceDate;
	
	private List<InvoiceUserBean> invoiceUsers;
	
	private String projectName;
	
	private String statusName;
	
	private Integer id; 

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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getPoId() {
		return poId;
	}

	public void setPoId(Integer poId) {
		this.poId = poId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getInvoiceFile() {
		return invoiceFile;
	}

	public void setInvoiceFile(String invoiceFile) {
		this.invoiceFile = invoiceFile;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public List<InvoiceUserBean> getInvoiceUsers() {
		return invoiceUsers;
	}

	public void setInvoiceUsers(List<InvoiceUserBean> invoiceUsers) {
		this.invoiceUsers = invoiceUsers;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public void importModel(InvoiceSearch invoice) {
		setEndDate(invoice.getEndDate());
		setStartDate(invoice.getStartDate());
		setInvoiceDate(invoice.getInvoiceDate());
		setInvoiceId(invoice.getInvoiceId());
		setStatusId(invoice.getStatusId());
		setStatusName(invoice.getStatusName());
		setProjectName(invoice.getProjectName());
		setId(invoice.getId());
		setPoId(invoice.getPoId());
		setPoNumber(invoice.getPoNumber());
		setProjectId(invoice.getProjectId());
		setTotal(invoice.getTotal());
	}

	public void importModel(com.minion.model.Invoice invoice) {
		
	}
	
}