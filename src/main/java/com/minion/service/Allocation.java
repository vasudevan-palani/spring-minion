package com.minion.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minion.model.Project;
import com.minion.model.ProjectAllocation;
import com.minion.repo.AllocationRepository;
import com.minion.repo.ProjectRepository;
import com.minion.repo.UserRepository;
import com.minion.service.request.AddAllocationRequest;

@Component
public class Allocation {

	@Autowired
	UserRepository userRepo;

	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	AllocationRepository allocationRepo;

	@Autowired
	User userService;

	@Autowired
	ErrorMsg errorService;

	public void addAllocation(AddAllocationRequest request) {
		
		//Get projectId
		Project project = projectRepo.findByProjectId(request.getProjectESAId());
		
		//Get userId
		com.minion.model.User user = userRepo.findByEmpId(request.getEmpId());
		
		//Add the allocation
		ProjectAllocation allocation = new ProjectAllocation();
		
		allocation.setBillingRate(request.getBillingRate());
		allocation.setStartDate(request.getStartDate());
		allocation.setEndDate(request.getEndDate());
		allocation.setPercent(request.getAllocation());
		allocation.setProjectId(project.getId());
		allocation.setUserId(user.getId());
		
		allocation.setCreatedDate(new java.sql.Date(new Date().getTime()));
		allocation.setModifiedDate(new java.sql.Date(new Date().getTime()));
		
		allocationRepo.save(allocation);
		
		if(!isValidAllocation(request)){
			allocationRepo.delete(allocation);
		}
		
	}

	private boolean isValidAllocation(AddAllocationRequest request) {

		//Get projectId
		Project project = projectRepo.findByProjectId(request.getProjectESAId());
		
		//Get userId
		com.minion.model.User user = userRepo.findByEmpId(request.getEmpId());
		
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(request.getStartDate());

		while (!gcal.getTime().after(request.getEndDate())) {
		    Date d = gcal.getTime();
		    
		    
		    Integer percent = allocationRepo.getAllocationPercentage(project.getId(), user.getId(), d);
		    if (percent > 100){
		    	return false;
		    }
		    gcal.add(Calendar.MONTH, 1);
		}
		
		return true;
	}
}