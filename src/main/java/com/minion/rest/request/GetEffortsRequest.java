package com.minion.rest.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.minion.service.bean.UserProjectAllocationBean;

public class GetEffortsRequest extends Request{
	private List<UserProjectAllocationBean> allocation;

	private String startDate;
	private String endDate;
	
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

	
	public List<UserProjectAllocationBean> getAllocation() {
		return allocation;
	}

	public void setAllocation(List<UserProjectAllocationBean> allocation) {
		this.allocation = allocation;
	}

	public com.minion.service.bean.request.GetEffortsRequest getServiceRequest() {
		com.minion.service.bean.request.GetEffortsRequest request= new com.minion.service.bean.request.GetEffortsRequest();
		request.setAllocation(getAllocation());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			request.setStartDate(new java.sql.Date(format.parse(getStartDate()).getTime()));
			request.setEndDate(new java.sql.Date(format.parse(getEndDate()).getTime()));
			request.setAllocation(getAllocation());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return request;
	}

}
