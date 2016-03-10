package com.minion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity(name = "invoice_sheets")
public class InvoiceSheets {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name="vendor_name")
	private String vendorName;
	@Column(name="full_name")
	private String fullName;
	@Column(name="invoice")
	private String invoiceNum;
	@Column(name="qty")
	private double qtyInvoiced;
	@Column(name="unit_price")
	private double unitPrice;
	private double amount;
	@Column(name="invoice_amount")
	private double invoiceAmt;
	@Column(name="po")
	private String poNum;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	private Date created;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public double getQtyInvoiced() {
		return qtyInvoiced;
	}
	public void setQtyInvoiced(double qtyInvoiced) {
		this.qtyInvoiced = qtyInvoiced;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getInvoiceAmt() {
		return invoiceAmt;
	}
	public void setInvoiceAmt(double invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}
	public String getPoNum() {
		return poNum;
	}
	public void setPoNum(String poNum) {
		this.poNum = poNum;
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
	public Date getCreated(java.sql.Date date) {
		// TODO Auto-generated method stub
		return created;
	}
	public void setCreated(java.sql.Date date) {
		// TODO Auto-generated method stub
		this.created=date;
	}
	
	
}
