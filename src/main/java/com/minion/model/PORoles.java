package com.minion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Po_roles")
public class PORoles {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="po_id")
	private Integer poId;
	
	@Column(name="quantity")
	private Float quantity;
	
	@Column(name="rate")
	private Float rate;
	
	@Column(name="total")
	private Float total;

	@Column(name="rolesdesc")
	private String rolesDescription;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPoId() {
		return poId;
	}

	public void setPoId(Integer poId) {
		this.poId = poId;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public String getRolesDescription() {
		return rolesDescription;
	}

	public void setRolesDescription(String rolesDescription) {
		this.rolesDescription= rolesDescription;
	}
	
	
}
