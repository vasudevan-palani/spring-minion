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

import com.minion.rest.request.AddPurchaseOrderRequest;
import com.minion.rest.request.GetPurchaseOrderRequest;
import com.minion.rest.request.SearchPurchaseOrderRequest;
import com.minion.rest.request.UpdatePurchaseOrderRequest;
import com.minion.rest.response.GetPurchaseOrderResponse;
import com.minion.rest.response.Response;
import com.minion.rest.response.SearchPurchaseOrderResponse;
import com.minion.service.ErrorCodes;
import com.minion.service.ErrorMsg;
import com.minion.service.MinionServiceException;
import com.minion.service.PurchaseOrder;
import com.minion.service.User;
import com.minion.service.bean.PurchaseOrderBean;


@RestController
@RequestMapping("/purchaseorders")
public class PurchaseOrderController extends BaseController {

	@Autowired
	private User userService;

	@Autowired
	private PurchaseOrder poService;

	@Autowired
	private ErrorMsg errorMsgs;

	public User getUserService() {
		return userService;
	}

	public void setUserService(User userService) {
		this.userService = userService;
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
	public ResponseEntity<Response> add(@RequestBody AddPurchaseOrderRequest request) {
		Response response = new Response();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());
			poService.addPurchaseOrder(request.getServiceRequest());

			response.setErrorcode(ErrorCodes.SUCCESS);
			response.setInfoMsg(errorMsgs.getMsg(ErrorCodes.PO_ADD_SUCCESS));

		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = {"http://knowinminutes.com","http://localhost:8887"})
	@RequestMapping(produces = "application/json", value = "/search",method={RequestMethod.POST,RequestMethod.OPTIONS})
	public ResponseEntity<SearchPurchaseOrderResponse> search(@RequestBody SearchPurchaseOrderRequest request) {
		SearchPurchaseOrderResponse response = new SearchPurchaseOrderResponse();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());
			List<PurchaseOrderBean> pos = poService.searchPurchaseOrder(request.getServiceRequest());
			response.setPos(pos);
			response.setErrorcode(ErrorCodes.SUCCESS);

		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		}

		return new ResponseEntity<SearchPurchaseOrderResponse>(response, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = {"http://knowinminutes.com","http://localhost:8887"})
	@RequestMapping(produces = "application/json", value = "/get",method={RequestMethod.POST,RequestMethod.OPTIONS})
	public ResponseEntity<GetPurchaseOrderResponse> get(@RequestBody GetPurchaseOrderRequest request) {
		GetPurchaseOrderResponse response = new GetPurchaseOrderResponse();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());

			PurchaseOrderBean po = poService.getPurchaseOrder(request.getServiceRequest());
			response.setPo(po);
			response.setErrorcode(ErrorCodes.SUCCESS);


		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		}

		return new ResponseEntity<GetPurchaseOrderResponse>(response, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = {"http://knowinminutes.com","http://localhost:8887"})
	@RequestMapping(produces = "application/json", value = "/update",method={RequestMethod.POST,RequestMethod.OPTIONS})
	public ResponseEntity<Response> update(@RequestBody UpdatePurchaseOrderRequest request) {
		Response response = new Response();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());

			poService.updatePurchaseOrder(request.getServiceRequest());
			response.setErrorcode(ErrorCodes.SUCCESS);
			response.setInfoMsg(errorMsgs.getMsg(ErrorCodes.PO_UPDATE_SUCCESS));

		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
