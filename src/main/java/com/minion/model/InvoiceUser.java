package com.minion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Invoice_users")
public class InvoiceUser {

	@Id
	@GeneratedValue
	private String id;
	
	@Column(name="invoice_id")
	private Integer invoiceId;
	
	@Column(name="user_id")
	private Integer userId;
	
	private Integer hours;
	
	private Integer billing_rate;
	private Integer total;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public Integer getBilling_rate() {
		return billing_rate;
	}
	public void setBilling_rate(Integer billing_rate) {
		this.billing_rate = billing_rate;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
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
