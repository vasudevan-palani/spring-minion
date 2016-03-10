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
	
	private Float hours;
	
	@Column(name="billing_rate")
	private Integer billingRate;
	private Float total;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Float getHours() {
		return hours;
	}
	public void setHours(Float hours) {
		this.hours = hours;
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
	public Integer getBillingRate() {
		return billingRate;
	}
	public void setBillingRate(Integer billingRate) {
		this.billingRate = billingRate;
	}
	
	
}
