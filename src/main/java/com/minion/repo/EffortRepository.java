package com.minion.repo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.minion.model.Effort;

public interface EffortRepository extends CrudRepository<Effort, Serializable >{

	Effort findByAllocationIdAndDate(Integer allocationId,Date date);

	@Query("SELECT e FROM efforts e where e.allocationId=:allocationId and e.date between :startDate and :endDate")
	List<Effort> findEfforts(@Param("allocationId")Integer allocationId,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
}
