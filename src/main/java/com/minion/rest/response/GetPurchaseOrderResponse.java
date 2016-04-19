package com.minion.rest.response;

import com.minion.service.bean.PurchaseOrderBean;

public class GetPurchaseOrderResponse extends Response{
	public PurchaseOrderBean po;

	public PurchaseOrderBean getPo() {
		return po;
	}

	public void setPo(PurchaseOrderBean po) {
		this.po = po;
	}
	
	
}
