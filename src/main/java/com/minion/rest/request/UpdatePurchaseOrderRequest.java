package com.minion.rest.request;

import com.minion.service.bean.PurchaseOrderBean;

public class UpdatePurchaseOrderRequest {
	private String empId;
	private String password;
	
	private PurchaseOrderBean po;
	
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
	public PurchaseOrderBean getPo() {
		return po;
	}
	public void setPo(PurchaseOrderBean po) {
		this.po = po;
	}
	public com.minion.service.bean.request.UpdatePurchaseOrderRequest getServiceRequest() {
		com.minion.service.bean.request.UpdatePurchaseOrderRequest request = new com.minion.service.bean.request.UpdatePurchaseOrderRequest();
		request.setPo(po);
		return request;
	}
	
	
}
