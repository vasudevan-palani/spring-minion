package com.minion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Invoices")
public class Invoice {

	@Id
	@GeneratedValue
	private Integer id;

	private Date start_date;
	private Date end_date;

	@Column(name="project_id")
	private Integer projectId;
	
	@Column(name="po_id")
	private Integer poId;

	@Column(name = "invoice_id")
	private String invoiceId;
	
	private String total;
	private Integer status;
	
	@Column(name="invoice_file")
	private String invoiceFile;

	private Date invoice_date;
	
	
	
	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoice_id(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceFile() {
		return invoiceFile;
	}

	public void setInvoiceFile(String invoiceFile) {
		this.invoiceFile = invoiceFile;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
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

	

}
