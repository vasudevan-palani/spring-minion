package com.minion.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.Constants;
import com.minion.model.Invoice;
import com.minion.repo.InvoiceRepository;
import com.minion.repo.PORepository;
import com.minion.repo.StatusRepository;
import com.minion.repo.UserRepository;

@Component
public class InvoiceDao {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PORepository poRepo;

	@Autowired
	private StatusRepository statusRepo;

	@Autowired
	private InvoiceRepository invoiceRepo;

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

	public Invoice createInvoiceIfNotExists(String invoiceId, String startDate, String endDate, String invoiceDate, Integer projectId,
			Integer poId, Float total, String invoiceFile) throws ParseException {

		Invoice invoice = invoiceRepo.findByInvoiceId(invoiceId);
		SimpleDateFormat formatter1 = new SimpleDateFormat("MMMMM-yyyy");
		SimpleDateFormat formatter2 = new SimpleDateFormat("d-MMM-yyyy");
		SimpleDateFormat startDateformatter = null;
		SimpleDateFormat endDateformatter = null;
		SimpleDateFormat invoiceDateformatter = null;
		
		if(startDate.matches("\\d.*")){
			startDateformatter = formatter2;
		}
		else{
			startDateformatter = formatter1;
		}
		if(endDate != null && endDate.matches("\\d.*")){
			endDateformatter = formatter2;
		}
		else{
			endDateformatter = formatter1;
		}
		if(invoiceDate != null && invoiceDate.matches("\\d.*")){
			invoiceDateformatter = formatter2;
		}
		else{
			invoiceDateformatter = formatter1;
		}
		
		if (invoice != null) {
			System.out.println("Invoice Exists : " + invoiceId);
			return invoice;
		} else {

			invoice = new Invoice();
			invoice.setInvoice_id(invoiceId);
			if (endDate != null)
				invoice.setEnd_date(endDateformatter.parse(endDate));
			if (startDate != null) {
				System.out.println(startDate);
				
				invoice.setStart_date(startDateformatter.parse(startDate));
			}
			if (invoiceDate != null) {
				System.out.println(invoiceDate);
				
				invoice.setInvoice_date(invoiceDateformatter.parse(invoiceDate));
			}
			invoice.setPoId(poId);
			invoice.setProjectId(projectId);
			invoice.setTotal(total+"");
			invoice.setInvoiceFile(invoiceFile);
			invoice.setStatus(statusRepo.findByName(Constants.NEW_STATUS).getId());
			invoiceRepo.save(invoice);
			System.out.println("Invoice Created : " + invoice.getId() + "," + invoiceId);
			return invoice;
		}

	}

}
