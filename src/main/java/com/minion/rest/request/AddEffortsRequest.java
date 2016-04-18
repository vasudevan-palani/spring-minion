package com.minion.rest.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddEffortsRequest extends Request {
	private int allocationId;
	private ArrayList<EffortItem> efforts;

	public int getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}

	public ArrayList<EffortItem> getEfforts() {
		return efforts;
	}

	public void setEfforts(ArrayList<EffortItem> efforts) {
		this.efforts = efforts;
	}

	public com.minion.service.bean.request.AddEffortsRequest getServiceRequest(){
		com.minion.service.bean.request.AddEffortsRequest request = new com.minion.service.bean.request.AddEffortsRequest();
		request.setAllocationId(allocationId);
		for(EffortItem item : getEfforts()){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				com.minion.service.bean.EffortItem serviceItem = new com.minion.service.bean.EffortItem();
				serviceItem.setDate(new java.sql.Date(format.parse(item.getDate()).getTime()));
				serviceItem.setEffort(item.getEffort());
				request.getEfforts().add(serviceItem);
			} catch (ParseException e) {
			}			
		}

		return request;
	}
}
