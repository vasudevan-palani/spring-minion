package com.minion.rest.request;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GetEffortsRequest extends Request{
	private int allocationId;

	private String startDate;
	private String endDate;
	
	public int getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(int allocationId) {
		this.allocationId = allocationId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public com.minion.service.request.GetEffortsRequest getServiceRequest() {
		com.minion.service.request.GetEffortsRequest request= new com.minion.service.request.GetEffortsRequest();
		request.setAllocationId(getAllocationId());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			request.setStartDate(new java.sql.Date(format.parse(getStartDate()).getTime()));
			request.setEndDate(new java.sql.Date(format.parse(getEndDate()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return request;
	}

}
