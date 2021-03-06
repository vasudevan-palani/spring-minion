package com.minion.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.Constants;
import com.minion.model.Invoice;
import com.minion.model.view.InvoiceSearch;
import com.minion.repo.InvoiceRepository;
import com.minion.repo.InvoiceSearchRepository;
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
	
	@Autowired
	private InvoiceSearchRepository invoiceSearchRepo;

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

	public Invoice createInvoiceIfNotExists(String invoiceId, String startDate, String endDate, String invoiceDate,
			Integer projectId, Integer poId, Float total, String invoiceFile) throws ParseException {

		Invoice invoice = invoiceRepo.findByInvoiceId(invoiceId);
		SimpleDateFormat formatter1 = new SimpleDateFormat("MMMMM-yyyy");
		SimpleDateFormat formatter2 = new SimpleDateFormat("d-MMM-yyyy");
		SimpleDateFormat startDateformatter = null;
		SimpleDateFormat endDateformatter = null;
		SimpleDateFormat invoiceDateformatter = null;

		if (startDate.matches("\\d.*")) {
			startDateformatter = formatter2;
		} else {
			startDateformatter = formatter1;
		}
		if (endDate != null && endDate.matches("\\d.*")) {
			endDateformatter = formatter2;
		} else {
			endDateformatter = formatter1;
		}
		if (invoiceDate != null && invoiceDate.matches("\\d.*")) {
			invoiceDateformatter = formatter2;
		} else {
			invoiceDateformatter = formatter1;
		}

		if (invoice != null) {
			System.out.println("Invoice Exists : " + invoiceId);
			return invoice;
		} else {

			invoice = new Invoice();
			invoice.setInvoiceId(invoiceId);
			if (endDate != null)
				invoice.setEndDate(new Date(endDateformatter.parse(endDate).getTime()));
			if (startDate != null) {
				System.out.println(startDate);

				invoice.setStartDate(new Date(startDateformatter.parse(startDate).getTime()));
			}
			if (invoiceDate != null) {
				System.out.println(invoiceDate);

				invoice.setInvoiceDate(new Date(invoiceDateformatter.parse(invoiceDate).getTime()));
			}
			invoice.setPoId(poId);
			invoice.setProjectId(projectId);
			invoice.setTotal(total);
			invoice.setInvoiceFile(invoiceFile);
			invoice.setStatus(statusRepo.findByName(Constants.NEW_STATUS).getId());
			invoiceRepo.save(invoice);
			System.out.println("Invoice Created : " + invoice.getId() + "," + invoiceId);
			return invoice;
		}

	}

	public Invoice findInvoiceByInvoiceNumber(String invoiceNumber) {

		return invoiceRepo.findByInvoiceId(invoiceNumber);
	}

	public InvoiceSearch findInvoiceById(Integer Id) {

		return invoiceSearchRepo.findById(Id);
	}
	
	
	public List<InvoiceSearch> findInvoices(String invoiceId, Date startDate, Date endDate, Date invoiceDate, String empId,
			String poNumber, String projectId, String statusId) {
		return invoiceSearchRepo.findInvoices(invoiceId, startDate, endDate, invoiceDate, empId, poNumber, projectId,statusId);
	}

	public void save(com.minion.model.Invoice invoice) {
		invoiceRepo.save(invoice);

	}

	public void updateInvoice(com.minion.model.Invoice invoice) {
		invoiceRepo.save(invoice);
	}

	public void addInvoice(com.minion.model.Invoice invoice) {
		
		invoiceRepo.save(invoice);
	}
}
