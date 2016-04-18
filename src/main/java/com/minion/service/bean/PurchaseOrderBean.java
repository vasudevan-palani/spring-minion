package com.minion.service.bean;

import java.sql.Date;

import com.minion.model.view.PurchaseOrderSearch;

public class PurchaseOrderBean {

	private String id;
	private String poNumber;
	private String version;
	private Integer projectId;
	
	private String requester;
	private String Buyer;
	private Date requestedDate;
	
	private String projectName;
	
	
	
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



	public Integer getProjectId() {
		return projectId;
	}



	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}



	public String getRequester() {
		return requester;
	}



	public void setRequester(String requester) {
		this.requester = requester;
	}



	public String getBuyer() {
		return Buyer;
	}



	public void setBuyer(String buyer) {
		Buyer = buyer;
	}



	public Date getRequestedDate() {
		return requestedDate;
	}



	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public void importModel(PurchaseOrderSearch po) {
		
		setBuyer(po.getBuyer());
		setPoNumber(po.getPoNumber());
		setProjectId(po.getProjectId());
		setRequestedDate(po.getRequestedDate());
		setVersion(po.getVersion());
		setId(po.getId().toString());
		setProjectName(po.getProjectName());
		
		
	}

}
