package com.minion.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minion.rest.request.AddEffortsRequest;
import com.minion.rest.request.GetEffortsRequest;
import com.minion.rest.response.Response;
import com.minion.service.Efforts;
import com.minion.service.ErrorCodes;
import com.minion.service.ErrorMsg;
import com.minion.service.MinionServiceException;
import com.minion.service.User;
import com.minion.service.bean.EffortItem;

@RestController
@RequestMapping("/efforts")
public class EffortController extends BaseController {

	@Autowired
	private User userService;

	@Autowired
	private Efforts effortService;

	@Autowired
	private ErrorMsg errorMsgs;

	public User getUserService() {
		return userService;
	}

	public void setUserService(User userService) {
		this.userService = userService;
	}

	public Efforts getEffortService() {
		return effortService;
	}

	public void setEffortService(Efforts effortService) {
		this.effortService = effortService;
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

	@CrossOrigin(origins = {"http://knowinminutes.com","http://localhost:8887"})
	@RequestMapping(produces = "application/json", value = "/add",method={RequestMethod.POST,RequestMethod.OPTIONS})
	public ResponseEntity<Response> add(@RequestBody AddEffortsRequest request) {
		Response response = new Response();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());
			effortService.addEfforts(request.getServiceRequest());

			response.setErrorcode(ErrorCodes.SUCCESS);
			response.setInfoMsg(errorMsgs.getMsg(ErrorCodes.EFFORT_ADD_SUCCESS));

		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		} 

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = {"http://knowinminutes.com","http://localhost:8887"})
	@RequestMapping(produces = "application/json", value = "/getEfforts",method={RequestMethod.POST,RequestMethod.OPTIONS})
	public ResponseEntity<Response> getEfforts(@RequestBody GetEffortsRequest request) {
		Response response = new Response();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());
			System.out.println(request.getStartDate());
			System.out.println(request.getEndDate());
			List<EffortItem> efforts = effortService.getEfforts(request.getServiceRequest());
			System.out.println(efforts.size());
			response.setObject(efforts);
			response.setErrorcode(ErrorCodes.SUCCESS);

		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		} 

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
