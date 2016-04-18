package com.minion.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.PO;

public interface PORepository extends CrudRepository<PO, Serializable >{

	List<PO> findByPoNumber(String poNumber);

	PO findByPoNumberAndPoVersion(String poNumber, String poRevision);

	List<PO> findByProjectId(Integer projectId);
	
}
