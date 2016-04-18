package com.minion.model.view;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "po_searchs")
public class PurchaseOrderSearch {

	@Id
	private String id;
	
	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "po_number")
	private String poNumber;

	@Column(name = "version")
	private String version;

	@Column(name = "buyer")
	private String buyer;

	@Column(name = "requested_date")
	private Date requestedDate;

	@Column(name = "project_number")
	private String projectESAId;

	@Column(name = "name")
	private String projectName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
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

	
}