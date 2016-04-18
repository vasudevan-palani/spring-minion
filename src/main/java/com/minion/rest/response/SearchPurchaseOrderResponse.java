package com.minion.rest.response;

import java.util.List;

import com.minion.service.bean.PurchaseOrderBean;

public class SearchPurchaseOrderResponse extends Response{
	public List<PurchaseOrderBean> pos;

	public List<PurchaseOrderBean> getPos() {
		return pos;
	}

	public void setPos(List<PurchaseOrderBean> pos) {
		this.pos = pos;
	}
	
	
}
