package com.minion.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.Constants;
import com.minion.ResourceBean;
import com.minion.model.Invoice;
import com.minion.model.InvoiceUser;
import com.minion.repo.InvoiceRepository;
import com.minion.repo.InvoiceUserRepository;
import com.minion.repo.PORepository;
import com.minion.repo.StatusRepository;
import com.minion.repo.UserRepository;

@Component
public class InvoiceUserDao {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PORepository poRepo;

	@Autowired
	private StatusRepository statusRepo;

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	private InvoiceUserRepository invoiceUserRepo;
	
	
	public InvoiceUserRepository getInvoiceUserRepo() {
		return invoiceUserRepo;
	}

	public void setInvoiceUserRepo(InvoiceUserRepository invoiceUserRepo) {
		this.invoiceUserRepo = invoiceUserRepo;
	}

	public InvoiceRepository getInvoiceRepo() {
		return invoiceRepo;
	}

	public void setInvoiceRepo(InvoiceRepository invoiceRepo) {
		this.invoiceRepo = invoiceRepo;
	}

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

	public PORepository getPoRepo() {
		return poRepo;
	}

	public void setPoRepo(PORepository poRepo) {
		this.poRepo = poRepo;
	}

	public InvoiceUser createInvoiceUserIfNotExists(Invoice inv,ResourceBean bean) {

		InvoiceUser invoice = invoiceUserRepo.findByInvoiceIdAndUserId(inv.getId(),bean.getId());

		if (invoice != null) {
			System.out.println("InvoiceUser Exists : " + invoice.getId());
			return invoice;
		} else {

			invoice = new InvoiceUser();
			invoice.setInvoiceId(inv.getId());
			invoice.setUserId(bean.getId());
			invoice.setHours(Math.round(bean.getHours()));
			invoice.setBilling_rate(Math.round(bean.getBillingRate()));
			invoice.setTotal((Math.round(bean.getTotal())));

			invoiceUserRepo.save(invoice);
			System.out.println("InvoiceUser Created : " + invoice.getId());
			return invoice;
		}

	}

}
