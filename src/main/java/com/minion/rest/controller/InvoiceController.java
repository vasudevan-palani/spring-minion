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

import com.minion.rest.request.AddInvoiceRequest;
import com.minion.rest.request.GetInvoiceRequest;
import com.minion.rest.request.SearchInvoiceRequest;
import com.minion.rest.request.UpdateInvoiceRequest;
import com.minion.rest.response.GetInvoiceResponse;
import com.minion.rest.response.Response;
import com.minion.rest.response.SearchInvoiceResponse;
import com.minion.service.ErrorCodes;
import com.minion.service.ErrorMsg;
import com.minion.service.Invoice;
import com.minion.service.MinionServiceException;
import com.minion.service.User;
import com.minion.service.bean.InvoiceBean;


@RestController
@RequestMapping("/invoice")
public class InvoiceController extends BaseController {

	@Autowired
	private User userService;

	
	@Autowired
	private Invoice invoiceService;

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
	public ResponseEntity<Response> add(@RequestBody AddInvoiceRequest request) {
		Response response = new Response();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());
			invoiceService.addInvoice(request.getServiceRequest());

			response.setErrorcode(ErrorCodes.SUCCESS);
			response.setInfoMsg(errorMsgs.getMsg(ErrorCodes.INVOICE_ADD_SUCCESS));

		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = {"http://knowinminutes.com","http://localhost:8887"})
	@RequestMapping(produces = "application/json", value = "/search",method={RequestMethod.POST,RequestMethod.OPTIONS})
	public ResponseEntity<SearchInvoiceResponse> search(@RequestBody SearchInvoiceRequest request) {
		SearchInvoiceResponse response = new SearchInvoiceResponse();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());
			List<InvoiceBean> invoices = invoiceService.queryInvoice(request.getServiceRequest());
			response.setInvoices(invoices);
			response.setErrorcode(ErrorCodes.SUCCESS);

		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		}

		return new ResponseEntity<SearchInvoiceResponse>(response, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = {"http://knowinminutes.com","http://localhost:8887"})
	@RequestMapping(produces = "application/json", value = "/get",method={RequestMethod.POST,RequestMethod.OPTIONS})
	public ResponseEntity<GetInvoiceResponse> get(@RequestBody GetInvoiceRequest request) {
		GetInvoiceResponse response = new GetInvoiceResponse();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());

			InvoiceBean invoice = invoiceService.getInvoice(request.getServiceRequest());
			response.setInvoice(invoice);
			response.setErrorcode(ErrorCodes.SUCCESS);


		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		}

		return new ResponseEntity<GetInvoiceResponse>(response, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = {"http://knowinminutes.com","http://localhost:8887"})
	@RequestMapping(produces = "application/json", value = "/update",method={RequestMethod.POST,RequestMethod.OPTIONS})
	public ResponseEntity<Response> update(@RequestBody UpdateInvoiceRequest request) {
		Response response = new Response();
		try {
			userService.authenticate(request.getEmpId(), request.getPassword());

			invoiceService.updateInvoice(request.getServiceRequest());
			
			
			response.setErrorcode(ErrorCodes.SUCCESS);
			response.setInfoMsg(errorMsgs.getMsg(ErrorCodes.PO_UPDATE_SUCCESS));

		} catch (MinionServiceException exception) {
			response.setErrorcode(exception.getErrorCode());
			response.setErrorMsg(exception.getErrorMsg());
		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
