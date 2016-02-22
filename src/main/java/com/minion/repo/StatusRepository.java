package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.Status;

public interface StatusRepository extends CrudRepository<Status, Serializable >{

	Status findByName(String name);
	
}
