package com.minion.loader.excel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import com.minion.Utils;
import com.minion.loader.RowBean;
import com.minion.loader.RowTransformer;
import com.minion.model.KronosSheets;
import com.minion.model.KronosUserMapping;
import com.minion.model.User;
import com.minion.repo.KronosHoursRepository;
import com.minion.repo.KronosSheetsRepository;
import com.minion.repo.KronosUserMappingsRepository;
import com.minion.repo.UserRepository;

@Component
public class KronosBeanRowTransformer implements RowTransformer {

	private KronosHoursRepository kronosHoursrepo;
	private UserRepository userRepo;
	private KronosSheetsRepository kronosSheetsRepo;
	private KronosUserMappingsRepository kronosUserMappingRepo;

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

	public KronosUserMappingsRepository getKronosUserMappingRepo() {
		return kronosUserMappingRepo;
	}

	public void setKronosUserMappingRepo(KronosUserMappingsRepository kronosUserMappingRepo) {
		this.kronosUserMappingRepo = kronosUserMappingRepo;
	}

	@Override
	public void transform(RowBean row) {
		KronosBean bean = (KronosBean) row;

		getUser(bean.getName());

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

	private KronosUserMapping findUserMapping(String name) {
		return kronosUserMappingRepo.findByName(name);
	}

	private User getUser(String name) {

		KronosUserMapping userMapping = findUserMapping(name);
		String userId = null;
		if (userMapping == null) {
			List<User> suggestions = findSuggestions(name);

			for (Iterator iterator = suggestions.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				int choice = getConfirmation(user, name);
				if (choice == 1) {
					createMapping(user, name);
					return user;
				} else if (choice == 2) {
					return null;
				}
			}
		}

		if (userId != null && userId != "") {
			return userRepo.findOne(userId);
		}
		return null;

	}

	private void createMapping(User user, String name) {
		KronosUserMapping map = new KronosUserMapping();
		map.setCreated(new java.sql.Date((new Date()).getTime()));
		map.setName(name);
		map.setUserId(user.getId());
		kronosUserMappingRepo.save(map);

	}

	private int getConfirmation(User user, String name) {
		System.out.print("User [ " + user.getFirstName() + "," + user.getLastName() + " ] => " + name + " ? (Y/N) : ");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
//		try {
//			System.out.println("Here >> ");
//			str = br.readLine();
//			System.out.println("Here >>> ");
//			if (str.equalsIgnoreCase("y")) {
//				return 1;
//			} else if (str.equalsIgnoreCase("n")) {
//				return 0;
//			} else if (str.equalsIgnoreCase("z")) {
//				return 2;
//			}
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return 2;

	}

	private List<User> findSuggestions(String name) {

		String[] names = name.split(",");
		List<User> suggestions = new ArrayList<User>();
		User user = null;
		if (names.length == 2) {
			Utils.log(LogLevel.DEBUG, "Trying to find user " + names[0] + "," + names[1]);
			user = userRepo.findByFirstNameAndLastName(names[0], names[1]);

			List<User> lastNameUsers;
			List<User> firstNameUsers;

			lastNameUsers = userRepo.findByLastName(names[0]);

			firstNameUsers = userRepo.findByFirstName(names[1]);

			if (lastNameUsers == null) {
				lastNameUsers = new ArrayList<User>();
			}
			if (firstNameUsers == null) {
				firstNameUsers = new ArrayList<User>();
			}

			suggestions.addAll(firstNameUsers);
			suggestions.addAll(lastNameUsers);
		}
		return suggestions;
	}

}
