package com.minion.repo;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.minion.model.User;

public interface UserRepository extends CrudRepository<User, Serializable> {

	public User findByFirstNameAndLastName(String firstName, String lastName);

	public User findByEmpIdAndPassword(String empId, String password);
	
	public User findByEmpId(String empId);
}
