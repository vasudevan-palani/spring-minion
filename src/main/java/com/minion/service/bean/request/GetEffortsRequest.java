package com.minion.service.bean.request;

import java.sql.Date;
import java.util.List;

import com.minion.service.bean.UserProjectAllocationBean;

public class GetEffortsRequest {
	private List<UserProjectAllocationBean> allocation;


	private Date startDate;
	private Date endDate;

	public List<UserProjectAllocationBean> getAllocation() {
		return allocation;
	}

	public void setAllocation(List<UserProjectAllocationBean> allocation) {
		this.allocation = allocation;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
