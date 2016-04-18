package com.minion.rest.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddAllocationRequest{
	private String empId;
	private String password;

	private String projectESAId;
	
	private Integer allocation;
	
	private Integer billingRate;
	private String startDate;
	private String endDate;
	
	
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
	public String getProjectESAId() {
		return projectESAId;
	}
	public void setProjectESAId(String projectESAId) {
		this.projectESAId = projectESAId;
	}
	public Integer getAllocation() {
		return allocation;
	}
	public void setAllocation(Integer allocation) {
		this.allocation = allocation;
	}
	public Integer getBillingRate() {
		return billingRate;
	}
	public void setBillingRate(Integer billingRate) {
		this.billingRate = billingRate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public com.minion.service.bean.request.AddAllocationRequest getServiceRequest() {
		com.minion.service.bean.request.AddAllocationRequest request = new com.minion.service.bean.request.AddAllocationRequest();
		
		request.setEmpId(this.getEmpId());
		request.setBillingRate(getBillingRate());
		request.setAllocation(getAllocation());
		request.setProjectESAId(getProjectESAId());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			request.setStartDate(new java.sql.Date(format.parse(getStartDate()).getTime()));
			request.setEndDate(new java.sql.Date(format.parse(getEndDate()).getTime()));
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		
		return request;
	}
	
	
}
