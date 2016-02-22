package com.minion.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "project_allocations")
public class ProjectAllocation {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "billing_rate")
	private Integer billingRate;

	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "allocation")
	private Integer percent;

	@Column(name = "created")
	private Date createdDate;


	@Column(name = "modified")
	private Date modifiedDate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifedDate) {
		this.modifiedDate = modifedDate;
	}

	
	
}