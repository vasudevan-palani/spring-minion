package com.minion.service.bean.request;

import java.sql.Date;
import java.util.List;

import com.minion.service.bean.PurchaseOrderRoleBean;



public class AddPurchaseOrderRequest {
	private String poNumber;
	private String version;
	private Integer projectId;
	
	private String requester;
	private String Buyer;
	private Date requestedDate;
	
	private List<PurchaseOrderRoleBean> poRoles;

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

	public List<PurchaseOrderRoleBean> getPoRoles() {
		return poRoles;
	}

	public void setPoRoles(List<PurchaseOrderRoleBean> poRoles) {
		this.poRoles = poRoles;
	}
	
	
	
}
