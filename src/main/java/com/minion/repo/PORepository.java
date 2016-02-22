package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.PO;

public interface PORepository extends CrudRepository<PO, Serializable >{

	PO findByPoNumber(String poNumber);

	PO findByPoNumberAndPoVersion(String poNumber, String poRevision);
	
}
