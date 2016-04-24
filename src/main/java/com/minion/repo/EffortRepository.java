package com.minion.repo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.minion.model.Effort;

public interface EffortRepository extends CrudRepository<Effort, Serializable >{

	Effort findByPoRoleIdAndDate(Integer poRoleId,Date date);

	@Query("SELECT e FROM efforts e where e.poRoleId=:poRoleId and e.date between :startDate and :endDate")
	List<Effort> findEfforts(@Param("poRoleId")Integer allocationId,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
}
