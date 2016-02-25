package com.minion.loader;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BillingSheetBean {
	private Integer empId;
	private Date startDate;
	private Date endDate;
	private Integer billingRate;
	private Float approvedHours;
	private String projectESAId;
	private String projectName;
	private String percent;

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

	public Float getApprovedHours() {
		return approvedHours;
	}

	public void setApprovedHours(Float approvedHours) {
		this.approvedHours = approvedHours;
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

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public void parse(String line) throws ParseException {
		String[] cols = line.split(",");
		this.setEmpId(Integer.parseInt(cols[1]));
		this.setBillingRate(Math.round(Float.parseFloat(cols[6])));
		this.setApprovedHours(Float.parseFloat(cols[8]));
		this.setProjectESAId(cols[9]);
		this.setPercent(cols[9]);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.setStartDate(new java.sql.Date(format.parse(cols[4]).getTime()));
		this.setEndDate(new java.sql.Date(format.parse(cols[5]).getTime()));
	}
}
