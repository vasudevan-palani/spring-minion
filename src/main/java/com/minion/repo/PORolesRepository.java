package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.PORoles;

public interface PORolesRepository extends CrudRepository<PORoles, Serializable >{

	PORoles findByPoIdAndRolesDescription(Integer poId,String rolesDescription);
	
}
