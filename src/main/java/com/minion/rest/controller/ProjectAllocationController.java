package com.minion.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minion.rest.ErrorCodes;
import com.minion.rest.request.AddAllocationRequest;
import com.minion.rest.response.Response;
import com.minion.service.Allocation;
import com.minion.service.ErrorMsg;
import com.minion.service.MinionServiceException;
import com.minion.service.User;
import com.minion.service.exception.AddAllocationException;
import com.minion.service.exception.InvalidUserException;

@RestController
@RequestMapping("/allocations")
public class ProjectAllocationController extends BaseController {

	@Autowired
	private User userService;

	@Autowired
	private Allocation allocationService;

	@Autowired
	private ErrorMsg errorMsgs;

	public User getUserService() {
		return userService;
	}

	public void setUserService(User userService) {
		this.userService = userService;
	}

	public Allocation getAllocationService() {
		return allocationService;
	}

	public void setAllocationService(Allocation allocationService) {
		this.allocationService = allocationService;
	}

	public ErrorMsg getErrorMsgs() {
		return errorMsgs;
	}

	public void setErrorMsgs(ErrorMsg errorMsgs) {
		this.errorMsgs = errorMsgs;
	}

	public User getAuthUser() {
		return userService;
	}

	public void setAuthUser(User authUser) {
		this.userService = authUser;
	}

	@RequestMapping(produces = "application/json", value = "/add")
	public ResponseEntity<Response> add(@RequestBody AddAllocationRequest request) {
		Response response = new Response();
		try {
			System.out.println(request.getStartDate());
			userService.authenticate(request.getEmpId(), request.getPassword());
			allocationService.addAllocation(request.getServiceRequest());

			response.setErrorcode(ErrorCodes.SUCCESS);
			response.setInfoMsg(errorMsgs.getMsg(ErrorCodes.USER_LOGIN_SUCCESS));

		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		} 

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
