package com.minion.service.bean;

import java.sql.Date;
import java.util.List;

public class UserProjectAllocationBean {
	private Integer id;
	
	private Integer empId;

	private Date startDate;

	private Date endDate;

	private Integer billingRate;

	private Integer projectId;

	private Integer userId;

	private Integer percent;

	private String firstName;

	private String lastName;

	private String projectESAId;

	private String projectName;

	private List<EffortItem> efforts;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
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

	public Integer getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(Integer billingRate) {
		this.billingRate = billingRate;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProjectESAId() {
		return projectESAId;
	}

	public void setProjectESAId(String projectESAId) {
		this.projectESAId = projectESAId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<EffortItem> getEfforts() {
		return efforts;
	}

	public void setEfforts(List<EffortItem> efforts) {
		this.efforts = efforts;
	}

}
