package com.minion.rest.response;

import java.util.List;

import com.minion.service.bean.InvoiceBean;

public class SearchInvoiceResponse extends Response{
	
	List<InvoiceBean> invoices;

	public List<InvoiceBean> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<InvoiceBean> invoices) {
		this.invoices = invoices;
	}
	
	

}
