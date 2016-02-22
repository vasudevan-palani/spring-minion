package com.minion.repo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.minion.model.ProjectAllocation;

public interface AllocationRepository extends CrudRepository<ProjectAllocation, Serializable> {

	@Query("SELECT pa FROM project_allocations pa where pa.projectId = :projectId and "
			+ "pa.userId = :userId and :date between pa.startDate and pa.endDate")
	public List<ProjectAllocation> findAllocations(@Param("projectId") Integer projectId, @Param("userId") Integer userId,
			@Param("date") Date date);
	
	@Query("SELECT SUM(pa.percent) FROM project_allocations pa where pa.projectId = :projectId and "
			+ "pa.userId = :userId and :date between pa.startDate and pa.endDate")
	public Integer getAllocationPercentage(@Param("projectId") Integer projectId, @Param("userId") Integer userId,
			@Param("date") Date date);

}