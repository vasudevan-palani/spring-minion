package com.minion.repo.view;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.view.UserProjectAllocation;

public interface UserProjectAllocationRepository extends CrudRepository<UserProjectAllocation, Serializable >{

	List<UserProjectAllocation> findByEmpId(Integer empId);

	
}
