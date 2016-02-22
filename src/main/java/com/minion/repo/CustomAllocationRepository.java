package com.minion.repo;

import java.util.Date;

import com.minion.model.ProjectAllocation;

public interface CustomAllocationRepository {
	public ProjectAllocation findAllocation(Integer projectId,Integer userId,Date startdate,Date endDate );
}
