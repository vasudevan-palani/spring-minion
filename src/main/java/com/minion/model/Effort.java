package com.minion.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="efforts")
public class Effort {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="allocation_id")
	private Integer allocationId;
	
	@Column(name="approved_hours")
	private Integer approvedHours;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="created")
	private Date created;

	@Column(name="modified")
	private Date modified;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(Integer allocationId) {
		this.allocationId = allocationId;
	}

	public Integer getApprovedHours() {
		return approvedHours;
	}

	public void setApprovedHours(Integer approvedHours) {
		this.approvedHours = approvedHours;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
}
