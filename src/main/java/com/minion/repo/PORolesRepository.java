package com.minion.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.PORoles;

public interface PORolesRepository extends CrudRepository<PORoles, Serializable >{

	PORoles findByPoIdAndRolesDescription(Integer poId,String rolesDescription);

	List<PORoles> findByPoId(Integer id);
	
}
