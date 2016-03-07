package com.minion.loader.excel;

import org.springframework.stereotype.Component;

import com.minion.loader.RowBean;
import com.minion.loader.RowTransformer;
import com.minion.model.User;
import com.minion.repo.KronosHoursRepository;
import com.minion.repo.UserRepository;

@Component
public class KronosBeanRowTransformer implements RowTransformer{

	
	private KronosHoursRepository kronosHoursrepo;
	private UserRepository userRepo;
	

	public KronosHoursRepository getKronosHoursrepo() {
		return kronosHoursrepo;
	}


	public void setKronosHoursrepo(KronosHoursRepository kronosHoursrepo) {
		this.kronosHoursrepo = kronosHoursrepo;
	}


	public UserRepository getUserRepo() {
		return userRepo;
	}


	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}


	@Override
	public void transform(RowBean row) {
		KronosBean bean = (KronosBean) row;
		String[] names = bean.getName().split(",");
		
		User user = userRepo.findByFirstNameAndLastName(names[0], names[1]);
		
		//repo.findAll();
	}

}
