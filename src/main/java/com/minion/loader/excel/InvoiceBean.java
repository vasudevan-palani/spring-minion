package com.minion.loader.excel;

import java.sql.Date;

public class InvoiceBean {

	private String vendorName;
	private String fullName;
	private String invoiceNum;
	private double qtyInvoiced;
	private double unitPrice;
	private double amount;
	private double invoiceAmt;
	private String poNum;
	private Date startDate;
	private Date endDate;
	
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
	
	@Override
	public String toString(){
		StringBuilder objString = new StringBuilder();
		objString.append(vendorName).append("|").append(fullName).append("|").append(invoiceNum).append("|")
		.append(qtyInvoiced).append("|").append(unitPrice).append("|").append(amount).append("|")
		.append(invoiceAmt).append("|").append(poNum).append("|").append(startDate).append("|").append(endDate);
		return objString.toString();
	}
}


