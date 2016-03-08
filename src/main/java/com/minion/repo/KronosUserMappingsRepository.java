package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.KronosUserMapping;

public interface KronosUserMappingsRepository extends CrudRepository<KronosUserMapping, Serializable >{

	public KronosUserMapping findByName(String name);
	
}
