package com.minion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import com.minion.Utils;
import com.minion.dao.InvoiceDao;
import com.minion.dao.InvoiceUserDao;
import com.minion.dao.PODao;
import com.minion.dao.ProjectDao;
import com.minion.dao.UserDao;
import com.minion.model.InvoiceUser;
import com.minion.service.request.AddInvoiceUserItemRequest;
import com.minion.service.request.CreateOrUpdateInvoiceRequest;
import com.minion.service.request.CreateOrUpdateInvoiceUserItemRequest;
import com.minion.service.request.QueryInvoiceRequest;

/**
 * @author Vasudevan PALANI
 * 
 */
@Component
public class Invoice {

	@Autowired
	private UserDao userDao;

	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private InvoiceUserDao invoiceUserDao;
	
	@Autowired
	private PODao poDao;
	
	@Autowired
	private ProjectDao projectDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public InvoiceDao getInvoiceDao() {
		return invoiceDao;
	}

	public void setInvoiceDao(InvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
	}

	public InvoiceUserDao getInvoiceUserDao() {
		return invoiceUserDao;
	}

	public void setInvoiceUserDao(InvoiceUserDao invoiceUserDao) {
		this.invoiceUserDao = invoiceUserDao;
	}

	public PODao getPoDao() {
		return poDao;
	}

	public void setPoDao(PODao poDao) {
		this.poDao = poDao;
	}

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * This method will return the invoices matching the criteria
	 * 
	 * @return
	 */
	public List<com.minion.service.request.Invoice> queryInvoice(QueryInvoiceRequest request) {
		return null;
	}

	/**
	 * This method will add the invoice item to the database
	 * 
	 * @return
	 */
	public void addInvoiceUserItem(AddInvoiceUserItemRequest request) {

	}

	public void createOrUpdateInvoiceUserItem(CreateOrUpdateInvoiceUserItemRequest request) {

		// get the Invoice Id
		//
		com.minion.model.Invoice invoice = invoiceDao.findInvoiceByInvoiceNumber(request.getInvoiceNumber());

		// get the user Id
		//
		com.minion.model.User user = userDao.findByEmpId(request.getEmpId());

		if (invoice == null || user == null) {
			Utils.log(LogLevel.ERROR, "Invoice or User is null, Invoice id : " + request.getInvoiceNumber()
					+ " , User empId : " + request.getEmpId());
			return;
		}

		// query for existing records
		//
		com.minion.model.InvoiceUser invoiceUser = invoiceUserDao.findbyInvoiceIdAndUserId(invoice.getId(),
				user.getId());

		// Insert or update
		//
		if (invoiceUser != null) {
			// Update it
			invoiceUser.setBillingRate(request.getBillingRate());
			invoiceUser.setHours(request.getHours());
			invoiceUser.setTotal(request.getTotal());
			invoiceUserDao.save(invoiceUser);
		} else {
			// Insert it
			invoiceUser = new InvoiceUser();
			invoiceUser.setBillingRate(request.getBillingRate());
			invoiceUser.setHours(request.getHours());
			invoiceUser.setTotal(request.getTotal());
			invoiceUser.setInvoiceId(invoice.getId());
			invoiceUser.setUserId(user.getId());
			invoiceUserDao.save(invoiceUser);
		}

	}

	public void createOrUpdateInvoice(CreateOrUpdateInvoiceRequest request) {
		// get the Invoice Id
		//
		com.minion.model.Invoice invoice = invoiceDao.findInvoiceByInvoiceNumber(request.getInvoiceNumber());

		// get the PO Id
		//
		com.minion.model.PO po = poDao.findByPoNumber(request.getPoNumber());
		

		// get the Project Id
		//
		com.minion.model.Project project = projectDao.findByProjectId(request.getProjectNumber());

		if(po != null && project == null){
			project = projectDao.findById(po.getProjectId());
		}
		
		if(po == null || project == null){
			Utils.log(LogLevel.ERROR, "Invoice or PO is null, Invoice id : " + request.getInvoiceNumber()
			+ " , PO  : " + request.getPoNumber());
			return;
		}


		
		// Insert or update
		//
		if (invoice != null) {
			// Update it
			invoice.setInvoiceDate(request.getInvoiceDate());
			invoice.setInvoiceFile(request.getInvoiceFile());
			invoice.setTotal(request.getTotal());
			invoice.setProjectId(project.getId());
			invoice.setPoId(po.getId());
			invoiceDao.save(invoice);
		} else {
			// Insert it
			invoice = new com.minion.model.Invoice();
			invoice.setInvoiceId(request.getInvoiceNumber());
			invoice.setInvoiceDate(request.getInvoiceDate());
			invoice.setInvoiceFile(request.getInvoiceFile());
			invoice.setTotal(request.getTotal());
			invoice.setProjectId(project.getId());
			invoice.setPoId(po.getId());
			invoiceDao.save(invoice);
		}
	}
}
