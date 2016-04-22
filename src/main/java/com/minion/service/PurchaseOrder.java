package com.minion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.dao.PODao;
import com.minion.model.PO;
import com.minion.model.PORoles;
import com.minion.model.view.PurchaseOrderSearch;
import com.minion.repo.PORepository;
import com.minion.repo.PORolesRepository;
import com.minion.repo.view.PurchaseOrderSearchRepository;
import com.minion.service.bean.PurchaseOrderBean;
import com.minion.service.bean.PurchaseOrderRoleBean;
import com.minion.service.bean.request.AddPurchaseOrderRequest;
import com.minion.service.exception.DuplicatePurchaseOrderException;

@Component
public class PurchaseOrder {

	@Autowired
	PORepository poRepo;
	
	@Autowired
	PurchaseOrderSearchRepository poSearchRepo;
	
	@Autowired
	PORolesRepository poRoleRepo;
	
	@Autowired
	ErrorMsg errorService;
	
	@Autowired
	PODao poDao;
	
	public void addPurchaseOrder(AddPurchaseOrderRequest request){
		PO po =  poRepo.findByPoNumberAndPoVersion(request.getPoNumber(),request.getVersion());
		
		if(po!= null){
			throw new DuplicatePurchaseOrderException(ErrorCodes.DUPLICATE_PO,
					errorService.getMsg(ErrorCodes.DUPLICATE_PO));
		}
		
		po = new PO();
		
		po.setPoNumber(request.getPoNumber());
		po.setPoVersion(request.getVersion());
		po.setProjectId(request.getProjectId());
		po.setRequester(request.getRequester());
		po.setBuyer(request.getBuyer());
		po.setRequestedDate(request.getRequestedDate());
		
		poRepo.save(po);
		
		for (PurchaseOrderRoleBean bean : request.getPoRoles()) {
			PORoles role = new PORoles();
			role.setPoId(po.getId());
			role.setQuantity(bean.getQuantity());
			role.setRate(bean.getRate());
			role.setRolesDescription(bean.getRole());
			role.setTotal(bean.getTotal());
			poRoleRepo.save(role);
		}
	}

	public List<PurchaseOrderBean> searchPurchaseOrder(com.minion.service.bean.request.SearchPurchaseOrderRequest serviceRequest) {
		List<PurchaseOrderSearch> pos = null;
		List<PurchaseOrderBean> beans = new ArrayList<PurchaseOrderBean>();
		if(serviceRequest.getPoNumber() != null && !serviceRequest.getPoNumber().equalsIgnoreCase("")){
			 pos = poSearchRepo.findByPoNumber(serviceRequest.getPoNumber());
		}
		else if(serviceRequest.getProjectId() != null){
			pos = poSearchRepo.findByProjectId(serviceRequest.getProjectId());
		}
		if(pos != null){
			for (PurchaseOrderSearch po : pos) {
				PurchaseOrderBean bean = new PurchaseOrderBean();
				bean.importModel(po);
				beans.add(bean);
			}
		}
		return beans;
	}

	public PurchaseOrderBean getPurchaseOrder(
			com.minion.service.bean.request.GetPurchaseOrderRequest serviceRequest) {
		PO po = poRepo.findOne(serviceRequest.getPoId());

		PurchaseOrderBean responseBean = new PurchaseOrderBean();
		responseBean.importModel(po);
		
		List<PORoles> poRolesList = poRoleRepo.findByPoId(po.getId());
		
		List<PurchaseOrderRoleBean> roleList = new ArrayList<PurchaseOrderRoleBean>();
		
		for (PORoles poRole : poRolesList) {
			PurchaseOrderRoleBean roleBean = new PurchaseOrderRoleBean();
			roleBean.importRole(poRole);
			roleList.add(roleBean);
			
		}
		responseBean.setPoRoles(roleList);
		
		return responseBean;
	}

	public void updatePurchaseOrder(
			com.minion.service.bean.request.UpdatePurchaseOrderRequest serviceRequest) {
		PO po = beantoModel(serviceRequest.getPo());
		
		poRepo.save(po);
		
		for (PurchaseOrderRoleBean rb : serviceRequest.getPo().getPoRoles()) {
			PORoles pr = beantoModel(rb);
			pr.setPoId(po.getId());
			if(rb.getAdded()!= null && rb.getAdded() == 1){
				pr.setId(null);
			}
			if(rb.getDeleted()!= null && rb.getDeleted() == 1){
				poRoleRepo.delete(pr.getId());
			}
			else{
				poRoleRepo.save(pr);				
			}
		}
	}

	private PORoles beantoModel(PurchaseOrderRoleBean rb) {
		PORoles pr = new PORoles();
		pr.setId(rb.getId());
		pr.setQuantity(rb.getQuantity());
		pr.setRate(rb.getRate());
		pr.setRolesDescription(rb.getRole());
		pr.setTotal(rb.getTotal());
		pr.setPoId(rb.getPoId());
		return pr;
	}

	private PO beantoModel(PurchaseOrderBean pb) {
		PO po = new PO();
		po.setId(Integer.parseInt(pb.getId()));
		po.setPoNumber(pb.getPoNumber());
		po.setPoVersion(pb.getVersion().toString());
		po.setRequestedDate(pb.getRequestedDate());
		po.setProjectId(pb.getProjectId());
		po.setRequester(pb.getRequester());
		po.setBuyer(pb.getBuyer());
		
		return po;
	}

	public boolean isPurchaseOrderExist(String poNumber) {
		PO po = poDao.findByPoNumber(poNumber);
		return po != null;
	}

	public PO getPurchaseOrder(String poNumber) {
		return poDao.findByPoNumber(poNumber);
	}
}