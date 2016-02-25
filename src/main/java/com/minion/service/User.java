package com.minion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.repo.UserRepository;
import com.minion.service.exception.InvalidUserException;

@Component
public class User {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ErrorMsg errorService;
	

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public void authenticate(String empId, String password) {
		com.minion.model.User user = userRepo.findByEmpIdAndPassword(empId, password);
		if (user == null) {
			throw new InvalidUserException(ErrorCodes.USER_LOGIN_FAILURE,errorService.getMsg(ErrorCodes.USER_LOGIN_FAILURE));
		}
	}
}
