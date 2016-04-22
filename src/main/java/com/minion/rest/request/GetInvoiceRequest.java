package com.minion.rest.request;

public class GetInvoiceRequest {
	private String empId;
	private String password;
	
	private String invoiceId;

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

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public com.minion.service.bean.request.GetInvoiceRequest getServiceRequest() {
		com.minion.service.bean.request.GetInvoiceRequest request = new com.minion.service.bean.request.GetInvoiceRequest();
		request.setInvoiceId(Integer.parseInt(getInvoiceId()));
		return request;
	}

}
