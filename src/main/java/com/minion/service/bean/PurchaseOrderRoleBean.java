package com.minion.service.bean;

import com.minion.model.PORoles;

public class PurchaseOrderRoleBean {
	private String role;
	private Float rate;
	private Float quantity;
	private Float total;
	private Integer id;
	
	private Integer poId;
	
	private Integer added;
	private Integer deleted;
	
	private Integer userId;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getAdded() {
		return added;
	}
	public void setAdded(Integer added) {
		this.added = added;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	
	public Integer getPoId() {
		return poId;
	}
	public void setPoId(Integer poId) {
		this.poId = poId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void importRole(PORoles poRole) {
		setQuantity(poRole.getQuantity());
		setRate(poRole.getRate());
		setRole(poRole.getRolesDescription());
		setTotal(poRole.getTotal());
		setId(poRole.getId());
		setPoId(poRole.getPoId());
	}
}
