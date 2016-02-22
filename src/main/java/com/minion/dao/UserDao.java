package com.minion.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.Constants;
import com.minion.model.User;
import com.minion.repo.StatusRepository;
import com.minion.repo.UserRepository;

@Component
public class UserDao {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private StatusRepository statusRepo;

	public UserRepository getuserRepo() {
		return userRepo;
	}

	public void setuserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public StatusRepository getStatusRepo() {
		return statusRepo;
	}

	public void setStatusRepo(StatusRepository statusRepo) {
		this.statusRepo = statusRepo;
	}

	public Integer createUserIfNotExists(String firstName, String lastName) {

		User user = userRepo.findByFirstNameAndLastName(firstName, lastName);

		if (user != null) {
			System.out.println("User Exists : " + firstName);
			return user.getId();
		} else {

			user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword("password");
			user.setStatus(statusRepo.findByName(Constants.ACTIVE_STATUS).getId());
			userRepo.save(user);
			System.out.println("User Created : " + user.getId() + "," + firstName);
			return user.getId();
		}

	}

	public User findUserByFirstNameAndLastName(String firstName, String lastName) {
		return userRepo.findByFirstNameAndLastName(firstName, lastName);
		
	}

}
