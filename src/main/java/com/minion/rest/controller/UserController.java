package com.minion.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minion.rest.ErrorCodes;
import com.minion.rest.request.Request;
import com.minion.rest.response.Response;
import com.minion.service.ErrorMsg;
import com.minion.service.User;
import com.minion.service.exception.InvalidUserException;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

	@Autowired
	private User userService;

	@Autowired
	private ErrorMsg errorMsgs;

	public User getAuthUser() {
		return userService;
	}

	public void setAuthUser(User authUser) {
		this.userService = authUser;
	}

	@RequestMapping(produces = "application/json", value = "/login")
	public ResponseEntity<Response> login(@RequestBody Request request) {
		Response response = new Response();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());
			
			response.setErrorcode(ErrorCodes.SUCCESS);
			response.setInfoMsg(errorMsgs.getMsg(ErrorCodes.USER_LOGIN_SUCCESS));
			
		} catch (InvalidUserException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());

		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
