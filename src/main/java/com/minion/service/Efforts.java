package com.minion.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.Utils;
import com.minion.model.Effort;
import com.minion.model.ProjectAllocation;
import com.minion.repo.AllocationRepository;
import com.minion.repo.EffortRepository;
import com.minion.repo.UserRepository;
import com.minion.service.bean.EffortItem;
import com.minion.service.bean.GetEffortsBean;
import com.minion.service.bean.UserProjectAllocationBean;
import com.minion.service.bean.request.AddEffortsRequest;
import com.minion.service.bean.request.GetEffortsRequest;
import com.minion.service.exception.EffortAdditionException;

@Component
public class Efforts {

	@Autowired
	UserRepository userRepo;

	@Autowired
	AllocationRepository allocationRepo;


	@Autowired
	EffortRepository effortRepo;
	
	@Autowired
	User userService;

	@Autowired
	ErrorMsg errorService;

	public void addEfforts(AddEffortsRequest request) {
		
		for (GetEffortsBean bean : request.getRequest()) {
			UserProjectAllocationBean allocationBean = bean.getAllocation();
			
			ProjectAllocation allocation = allocationRepo.findOne(allocationBean.getId());
			for (EffortItem item : allocationBean.getEfforts()) {
				try {
					item.setDate(Utils.getSqlDate(item.getDateStr(),Utils.DATE_FORMAT));
					if (item.getDate().after(allocation.getEndDate()) || item.getDate().before(allocation.getStartDate())) {
						throw new EffortAdditionException(ErrorCodes.DATE_RANGE_INVALID,
								errorService.getMsg(ErrorCodes.DATE_RANGE_INVALID));
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new EffortAdditionException(ErrorCodes.DATE_RANGE_INVALID,
							errorService.getMsg(ErrorCodes.DATE_RANGE_INVALID));
				}

			}
			
			for (EffortItem item : allocationBean.getEfforts()) {
				Effort effort = effortRepo.findByAllocationIdAndDate(allocation.getId(), item.getDate());
				if( item.getEffort() > 0){
					if(effort == null){
						effort = new Effort();
					}

					effort.setAllocationId(allocation.getId());
					effort.setDate(item.getDate());
					effort.setApprovedHours(Math.round(item.getEffort()));
					effortRepo.save(effort);
				}
				else if(item.getEffort() == 0 && effort != null){
					effortRepo.delete(effort);
				}
			}
		}
		
		

		

	}
	
	public List<GetEffortsBean> getEfforts(GetEffortsRequest request) {
		
		List<GetEffortsBean> getEffortsList = new ArrayList<GetEffortsBean>();
		for (UserProjectAllocationBean allocation : request.getAllocation()) {
			
			GetEffortsBean bean = new GetEffortsBean();
			bean.setAllocation(allocation);
			
			
			List<Effort> efforts = effortRepo.findEfforts(allocation.getId(),request.getStartDate(),request.getEndDate());			
			List<EffortItem> effortItems = new ArrayList<EffortItem>();

			for (Effort effort : efforts) {
				EffortItem item = new EffortItem();
				item.setDate(effort.getDate());
				item.setDateStr(effort.getDate().toString());
				item.setEffort(effort.getApprovedHours());
				effortItems.add(item);
			}
			allocation.setEfforts(effortItems);
			getEffortsList.add(bean);
		}
		return getEffortsList;	
	}


}