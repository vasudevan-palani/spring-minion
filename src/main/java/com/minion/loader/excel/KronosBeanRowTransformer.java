package com.minion.loader.excel;

import java.sql.Date;
import java.util.List;

import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import com.minion.Utils;
import com.minion.loader.RowBean;
import com.minion.loader.RowTransformer;
import com.minion.model.KronosSheets;
import com.minion.model.User;
import com.minion.repo.KronosHoursRepository;
import com.minion.repo.KronosSheetsRepository;
import com.minion.repo.UserRepository;

@Component
public class KronosBeanRowTransformer implements RowTransformer {

	private KronosHoursRepository kronosHoursrepo;
	private UserRepository userRepo;
	private KronosSheetsRepository kronosSheetsRepo;

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

	public KronosSheetsRepository getKronosSheetsRepo() {
		return kronosSheetsRepo;
	}

	public void setKronosSheetsRepo(KronosSheetsRepository kronosSheetsRepo) {
		this.kronosSheetsRepo = kronosSheetsRepo;
	}

	@Override
	public void transform(RowBean row) {
		KronosBean bean = (KronosBean) row;

		User user = findUser(bean.getName());

		if (user == null) {
			Utils.log(LogLevel.ERROR, "User not found :" + bean.getName());
		} else {
			Utils.log(LogLevel.INFO, "User found :" + bean.getName() + " : " + user.getId());
		}

		KronosSheets ks = new KronosSheets();
		List<KronosSheets> ksList = null;
		String[] names = bean.getName().split(",");

		ks.setCreated(new java.sql.Date((new java.util.Date()).getTime()));
		ks.setDate(bean.getDate());
		ks.setHours(bean.getLoggedHours());
		ks.setProject(bean.getPayCode());
		ks.setFirst_name(names[1]);
		ks.setLast_name(names[0]);

		ksList = kronosSheetsRepo.findObject(ks.getDate(), ks.getHours(), ks.getLast_name(), ks.getFirst_name());

		if (ksList.size() <= 0) {
			kronosSheetsRepo.save(ks);
		}
	}

	private User findUser(String name) {
		String[] names = name.split(",");
		User user = null;
		if (names.length == 2) {
			Utils.log(LogLevel.DEBUG, "Trying to find user " + names[0] + "," + names[1]);
			user = userRepo.findByFirstNameAndLastName(names[0], names[1]);

			List<User> users;

			if (user == null) {
				user = userRepo.findByFirstNameAndLastName(names[1], names[0]);
			}

			if (user == null) {
				users = userRepo.findByLastName(names[0]);
				if (users != null && users.size() > 0) {
					user = users.get(0);
				}
			}
			if (user == null) {
				users = userRepo.findByFirstName(names[1]);
				if (users != null && users.size() > 0) {
					user = users.get(0);
				}
			}
			if (user == null) {
				user = userRepo.findByFirstNameAndLastName(names[1], names[0]);
			}

			if (user == null) {
				users = userRepo.findByLastName(names[0]);
				if (users != null && users.size() > 0) {
					user = users.get(0);
				}
			}
			if (user == null) {
				users = userRepo.findByFirstName(names[1]);
				if (users != null && users.size() > 0) {
					user = users.get(0);
				}
			}
		}
		return user;
	}

}
