package com.minion.service.request;

import java.util.ArrayList;

public class AddEffortsRequest {
	private int userId;
	private int allocationId;
	private ArrayList<EffortItem> efforts;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
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
	
	
}
