package com.minion.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.model.PORoleUser;
import com.minion.repo.PORoleUserRepository;

@Component
public class PORoleUserDao {

	@Autowired
	private PORoleUserRepository roleUserRepo;

	public PORoleUserRepository getRoleUserRepo() {
		return roleUserRepo;
	}

	public void setRoleUserRepo(PORoleUserRepository roleUserRepo) {
		this.roleUserRepo = roleUserRepo;
	}

	public PORoleUser findByRoleIdAndUserId(Integer roleId, Integer userId) {

		return roleUserRepo.findByRoleIdAndUserId(roleId, userId);
	}

	public void addUser(Integer id, Integer userId) {
		PORoleUser roleUser = new PORoleUser();
		roleUser.setRoleId(id);
		roleUser.setUserId(userId);
		roleUserRepo.save(roleUser);
	}

	public void update(PORoleUser roleUser) {
		if (roleUser.getId() != null && !roleUser.getId().toString().equalsIgnoreCase("")) {
			roleUserRepo.save(roleUser);
		}
	}

	public PORoleUser findByRoleId(Integer id) {
		if(id != null){
			return roleUserRepo.findByRoleId(id);			
		}
		return null;
	}

}
