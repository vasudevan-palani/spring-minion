package com.minion.service.bean;

import java.sql.Date;
import java.util.List;

import com.minion.model.PO;
import com.minion.model.view.PurchaseOrderSearch;

public class PurchaseOrderBean {

	private String id;
	private String poNumber;
	private Integer version;
	private Integer projectId;

	private String requester;
	private String buyer;
	private Date requestedDate;

	private String projectName;

	private List<PurchaseOrderRoleBean> poRoles;

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
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

	public List<PurchaseOrderRoleBean> getPoRoles() {
		return poRoles;
	}

	public void setPoRoles(List<PurchaseOrderRoleBean> poRoles) {
		this.poRoles = poRoles;
	}

	public void importModel(PurchaseOrderSearch po) {

		setBuyer(po.getBuyer());
		setPoNumber(po.getPoNumber());
		setProjectId(po.getProjectId());
		setRequestedDate(po.getRequestedDate());
		if(po.getVersion() != null && !po.getVersion().equalsIgnoreCase(""))
		setVersion(Integer.parseInt(po.getVersion()));
		setId(po.getId().toString());
		setProjectName(po.getProjectName());

	}

	public void importModel(PO po) {

		setBuyer(po.getBuyer());
		setRequester(po.getRequester());
		setPoNumber(po.getPoNumber());
		setProjectId(po.getProjectId());
		setRequestedDate(po.getRequestedDate());
		if(po.getPoVersion() != null && !po.getPoVersion().equalsIgnoreCase(""))
		setVersion(Integer.parseInt(po.getPoVersion()));
		setId(po.getId().toString());

	}

}
