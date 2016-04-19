package com.minion.service.bean.request;

import com.minion.service.bean.PurchaseOrderBean;

public class UpdatePurchaseOrderRequest {

	private PurchaseOrderBean po;
	
	public PurchaseOrderBean getPo() {
		return po;
	}
	public void setPo(PurchaseOrderBean po) {
		this.po = po;
	}
}
