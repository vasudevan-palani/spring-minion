package com.minion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "credit_notes")
public class CreditNote {

	@Id
	@GeneratedValue
	private Integer id;

	private Date start_date;
	private Date end_date;

	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "po_id")
	private Integer poId;

	@Column(name = "credit_note_id")
	private String creditNoteId;

	private String total;
	private Integer status;

	@Column(name = "credit_note_file")
	private String creditNoteFile;

	@Column(name = "original_invoice_id")
	private String originalInvoiceId;

	@Column(name="credit_note_date")
	private Date creditNoteDate;
	
	
	
	
	public Date getCreditNoteDate() {
		return creditNoteDate;
	}

	public void setCreditNoteDate(Date creditNoteDate) {
		this.creditNoteDate = creditNoteDate;
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

	public String getCreditNoteId() {
		return creditNoteId;
	}

	public void setCreditNoteId(String creditNoteId) {
		this.creditNoteId = creditNoteId;
	}

	public String getCreditNoteFile() {
		return creditNoteFile;
	}

	public void setCreditNoteFile(String creditNoteFile) {
		this.creditNoteFile = creditNoteFile;
	}

	public String getOriginalInvoiceId() {
		return originalInvoiceId;
	}

	public void setOriginalInvoiceId(String originalInvoiceId) {
		this.originalInvoiceId = originalInvoiceId;
	}

}