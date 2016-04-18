package com.minion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}