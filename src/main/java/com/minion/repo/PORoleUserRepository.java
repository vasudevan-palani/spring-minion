package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.PORoleUser;

public interface PORoleUserRepository extends CrudRepository<PORoleUser, Serializable >{

	PORoleUser findByRoleIdAndUserId(Integer roleId, Integer userId);

	PORoleUser findByRoleId(Integer roleId);
	
}
