package com.minion.loader.excel;

import java.util.List;

import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import com.minion.Utils;
import com.minion.loader.RowBean;
import com.minion.loader.RowTransformer;
import com.minion.model.InvoiceSheets;
import com.minion.model.User;
import com.minion.repo.InvoiceSheetsRepository;
import com.minion.repo.UserRepository;

@Component
public class InvoiceBeanRowTransformer implements RowTransformer {

	private UserRepository userRepo;
	private InvoiceSheetsRepository invoiceSheetsRepo;


	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public InvoiceSheetsRepository getInvoiceSheetsRepo() {
		return invoiceSheetsRepo;
	}

	public void setInvoiceSheetsRepo(InvoiceSheetsRepository invoiceSheetsRepo) {
		this.invoiceSheetsRepo = invoiceSheetsRepo;
	}

	@Override
	public void transform(RowBean row) {
		InvoiceBean bean = (InvoiceBean) row;

		User user = findUser(bean.getFullName());

		if (user == null) {
			Utils.log(LogLevel.ERROR, "User not found :" + bean.getFullName());
		} else {
			Utils.log(LogLevel.INFO, "User found :" + bean.getFullName() + " : " + user.getId());
		}

		InvoiceSheets is = new InvoiceSheets();
		List<InvoiceSheets> isList = null;

		is.setCreated(new java.sql.Date((new java.util.Date()).getTime()));
		is.setStartDate(bean.getStartDate());
		is.setFullName(bean.getFullName());

		isList = invoiceSheetsRepo.findObject(is.getStartDate(), is.getInvoiceNum(), is.getFullName());

		if (isList.size() <= 0) {
			invoiceSheetsRepo.save(is);
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
