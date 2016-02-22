package com.minion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.model.ProjectAllocation;
import com.minion.repo.AllocationRepository;
import com.minion.repo.UserRepository;
import com.minion.rest.ErrorCodes;
import com.minion.service.exception.EffortAdditionException;
import com.minion.service.request.AddEffortsRequest;
import com.minion.service.request.EffortItem;

@Component
public class Efforts {

	@Autowired
	UserRepository userRepo;

	@Autowired
	AllocationRepository allocationRepo;

	@Autowired
	User userService;

	@Autowired
	ErrorMsg errorService;

	public void addEfforts(AddEffortsRequest request) {
		ProjectAllocation allocation = allocationRepo.findOne(request.getAllocationId());
		for (EffortItem item : request.getEfforts()) {
			if (item.getDate().after(allocation.getEndDate()) || item.getDate().before(allocation.getStartDate())) {
				throw new EffortAdditionException(ErrorCodes.DATE_RANGE_INVALID,
						errorService.getMsg(ErrorCodes.DATE_RANGE_INVALID));
			}
		}
	}
}