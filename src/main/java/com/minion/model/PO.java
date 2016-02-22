package com.minion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Pos")
public class PO {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="po_number")
	private String poNumber;
	
	@Column(name="requester")
	private String requester;
	
	@Column(name="buyer")
	private String buyer;
	
	@Column(name="total")
	private Integer total;

	@Column(name="version")
	private String poVersion;
	
	@Column(name="project_id")
	private Integer projectId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String po_number) {
		this.poNumber = po_number;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getPoVersion() {
		return poVersion;
	}

	public void setPoVersion(String poVersion) {
		this.poVersion = poVersion;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	
}
