package com.minion.rest.request;

public class GetPurchaseOrderRequest{
	
	private String empId;
	private String password;
	
	private String poId;
	
	

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



	public String getPoId() {
		return poId;
	}



	public void setPoId(String poId) {
		this.poId = poId;
	}



	public com.minion.service.bean.request.GetPurchaseOrderRequest getServiceRequest() {
		com.minion.service.bean.request.GetPurchaseOrderRequest request = new com.minion.service.bean.request.GetPurchaseOrderRequest();
		
		request.setPoId(Integer.parseInt(getPoId()));
		
		return request;
	}

	
}
