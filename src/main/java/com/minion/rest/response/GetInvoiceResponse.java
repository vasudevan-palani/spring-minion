package com.minion.rest.response;

import com.minion.service.bean.InvoiceBean;

public class GetInvoiceResponse extends Response{
	private InvoiceBean invoice;

	public InvoiceBean getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceBean invoice) {
		this.invoice = invoice;
	}


	
}
