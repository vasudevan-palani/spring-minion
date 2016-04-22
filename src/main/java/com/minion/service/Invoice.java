package com.minion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import com.minion.Mapper;
import com.minion.Utils;
import com.minion.dao.InvoiceDao;
import com.minion.dao.InvoiceUserDao;
import com.minion.dao.PODao;
import com.minion.dao.ProjectDao;
import com.minion.dao.UserDao;
import com.minion.model.InvoiceUser;
import com.minion.model.PO;
import com.minion.service.bean.InvoiceUserBean;
import com.minion.service.bean.request.AddInvoiceRequest;
import com.minion.service.bean.request.AddInvoiceUserItemRequest;
import com.minion.service.bean.request.CreateOrUpdateInvoiceRequest;
import com.minion.service.bean.request.CreateOrUpdateInvoiceUserItemRequest;
import com.minion.service.bean.request.GetInvoiceRequest;
import com.minion.service.bean.request.QueryInvoiceRequest;
import com.minion.service.bean.request.UpdateInvoiceRequest;
import com.minion.service.exception.DuplicateInvoiceException;

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

	@Autowired
	ErrorMsg errorService;
	
	@Autowired
	PurchaseOrder poService;
	
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
	 * This method will add an invoice
	 * 
	 * @return
	 */
	public void addInvoice(AddInvoiceRequest request) {
		
		if(isInvoiceExist(request.getInvoice().getInvoiceId())){
			throw new DuplicateInvoiceException(ErrorCodes.DUPLICATE_INVOICE,errorService.getMsg(ErrorCodes.DUPLICATE_INVOICE));
		}
		
		if(!poService.isPurchaseOrderExist(request.getInvoice().getPoNumber())){
			throw new DuplicateInvoiceException(ErrorCodes.PO_DO_NOT_EXIST,errorService.getMsg(ErrorCodes.PO_DO_NOT_EXIST));
		}
		
		PO po = poService.getPurchaseOrder(request.getInvoice().getPoNumber());
		
		com.minion.model.Invoice invoice = new com.minion.model.Invoice();
		Mapper.map(request.getInvoice(),invoice);
		invoice.setPoId(po.getId());
		
		invoiceDao.addInvoice(invoice);
		
		for (InvoiceUserBean invoiceUserBean : request.getInvoice().getInvoiceUsers()) {
			com.minion.model.InvoiceUser invoiceUser = new com.minion.model.InvoiceUser();
			invoiceUserBean.setInvoiceId(invoice.getId());
			Mapper.map(invoiceUserBean,invoiceUser);
			invoiceUserDao.addInvoiceUser(invoiceUser);
		}		
	}
	
	private boolean isInvoiceExist(String invoiceId) {
		com.minion.model.Invoice invoice = invoiceDao.findInvoiceByInvoiceNumber(invoiceId);
		return invoice != null;
	}

	/**
	 * This method will return the invoices matching the criteria
	 * 
	 * @return
	 */
	public List<com.minion.service.bean.InvoiceBean> queryInvoice(QueryInvoiceRequest request) {
		List<com.minion.service.bean.InvoiceBean> ibs = new ArrayList<com.minion.service.bean.InvoiceBean>();
		List<com.minion.model.view.InvoiceSearch> invoices = invoiceDao.findInvoices(request.getInvoiceNumber(), request.getBillingPeriod().getStartDate(),
				request.getBillingPeriod().getEndDate(), request.getInvoiceDate(), "", request.getPoNumber(),
				request.getProjectId(),request.getStatusId());
		
		for(com.minion.model.view.InvoiceSearch invoice : invoices){
			com.minion.service.bean.InvoiceBean ib = new com.minion.service.bean.InvoiceBean();
			ib.importModel(invoice);
			ibs.add(ib);
		}
		return ibs;
	}

	/**
	 * This method will return the invoice by Id
	 * 
	 * @return
	 */
	public com.minion.service.bean.InvoiceBean getInvoice(GetInvoiceRequest request) {
		com.minion.model.view.InvoiceSearch invoice = invoiceDao.findInvoiceById(request.getInvoiceId());
		List<InvoiceUser> invoiceUsers = invoiceUserDao.findbyInvoiceId(request.getInvoiceId());
		
		com.minion.service.bean.InvoiceBean ib = new com.minion.service.bean.InvoiceBean();
		ib.importModel(invoice);
		
		List<com.minion.service.bean.InvoiceUserBean> invoiceUserBeans = new ArrayList<com.minion.service.bean.InvoiceUserBean>();
		for (InvoiceUser invoiceUser: invoiceUsers) {
			com.minion.service.bean.InvoiceUserBean invoiceUserBean = new com.minion.service.bean.InvoiceUserBean();
			Mapper.map(invoiceUser,invoiceUserBean);
			invoiceUserBeans.add(invoiceUserBean);
		}
		ib.setInvoiceUsers(invoiceUserBeans);

		return ib;
	}
	
	/**
	 * This method will update the invoice
	 * 
	 * @return
	 */
	public void updateInvoice(UpdateInvoiceRequest request) {
		
		com.minion.model.Invoice invoice = new com.minion.model.Invoice();
		Mapper.map(request.getInvoice(),invoice);
		
		if(!Utils.notEmpty(invoice.getId())){
			return;
		}
		
		invoiceDao.updateInvoice(invoice);
		
		for (InvoiceUserBean invoiceUserBean : request.getInvoice().getInvoiceUsers()) {
			com.minion.model.InvoiceUser invoiceUser = new com.minion.model.InvoiceUser();
			Mapper.map(invoiceUserBean,invoiceUser);			
			if(invoiceUserBean.getAdded()!=null && invoiceUserBean.getAdded() == 1){
				invoiceUserDao.addInvoiceUser(invoiceUser);
			}
			else if(invoiceUserBean.getDeleted() != null && invoiceUserBean.getDeleted() == 1){
				invoiceUserDao.deleteInvoiceUser(invoiceUser);
			}
			else{
				invoiceUserDao.updateInvoiceUser(invoiceUser);				
			}
		}		
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
			invoiceUserDao.updateInvoiceUser(invoiceUser);
		} else {
			// Insert it
			invoiceUser = new InvoiceUser();
			invoiceUser.setBillingRate(request.getBillingRate());
			invoiceUser.setHours(request.getHours());
			invoiceUser.setTotal(request.getTotal());
			invoiceUser.setInvoiceId(invoice.getId());
			invoiceUser.setUserId(user.getId());
			invoiceUserDao.updateInvoiceUser(invoiceUser);
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

		if (po != null && project == null) {
			project = projectDao.findById(po.getProjectId());
		}

		if (po == null || project == null) {
			Utils.log(LogLevel.ERROR, "Invoice or PO is null, Invoice id : " + request.getInvoiceNumber() + " , PO  : "
					+ request.getPoNumber());
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
