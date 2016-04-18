package com.minion.service.bean.request;

import java.sql.Date;

public class AddAllocationRequest {

	private String projectESAId;
	private String empId;
	
	private Integer allocation;
	
	private Integer billingRate;
	private Date startDate;
	private Date endDate;
	public String getProjectESAId() {
		return projectESAId;
	}
	public void setProjectESAId(String projectESAId) {
		this.projectESAId = projectESAId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
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
	
}
