package com.minion.loader.excel;

import java.sql.Date;

import com.minion.loader.RowBean;

public class InvoiceBean implements RowBean{

	private String vendorName;
	private String fullName;
	private String invoiceNum;
	private Float qtyInvoiced;
	private Integer unitPrice;
	private Float amount;
	private Float invoiceAmt;
	private String poNum;
	private Date startDate;
	private Date endDate;
	
	private String empId;
	

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


	public Float getQtyInvoiced() {
		return qtyInvoiced;
	}


	public void setQtyInvoiced(Float qtyInvoiced) {
		this.qtyInvoiced = qtyInvoiced;
	}


	public Integer getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}


	public Float getAmount() {
		return amount;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}


	public Float getInvoiceAmt() {
		return invoiceAmt;
	}


	public void setInvoiceAmt(Float invoiceAmt) {
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


	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
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


