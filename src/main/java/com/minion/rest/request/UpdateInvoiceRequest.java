package com.minion.rest.request;

import com.minion.service.bean.InvoiceBean;

public class UpdateInvoiceRequest {
	private String empId;
	private String password;
	
	private InvoiceBean invoice;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public InvoiceBean getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceBean invoice) {
		this.invoice = invoice;
	}

	public com.minion.service.bean.request.UpdateInvoiceRequest getServiceRequest() {
		com.minion.service.bean.request.UpdateInvoiceRequest request = new com.minion.service.bean.request.UpdateInvoiceRequest();
		request.setInvoice(getInvoice());
		
		return request;
	}
	
	
}
