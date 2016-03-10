package com.minion.loader.excel;

import com.minion.loader.RowBean;
import com.minion.loader.RowTransformer;
import com.minion.service.Invoice;
import com.minion.service.request.CreateOrUpdateInvoiceRequest;
import com.minion.service.request.CreateOrUpdateInvoiceUserItemRequest;
import com.minion.service.request.QueryInvoiceRequest;

public class InvoiceBeanRowTransformer implements RowTransformer {

	private Invoice invoiceService;

	public Invoice getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(Invoice invoiceService) {
		this.invoiceService = invoiceService;
	}

	@Override
	public void transform(RowBean row) {
		InvoiceBean bean = (InvoiceBean) row;

		// Steps
		// 1. Find if invoice exists
		// 1.1 If Invoice exists -> check if invoice params are up to date,
		// else, update it
		// 1.2 If Invoice doesnt exist -> create them
		// 2. Find for the invoice above, if the corresponding invoice User
		// exists
		// 2.1 If the invoice user exists -> check if all the attributes are
		// updated else, update it
		// 2.2 If it doesnt exist , create them

		QueryInvoiceRequest invoiceRequest = new QueryInvoiceRequest();
		invoiceRequest.setInvoiceNumber(bean.getInvoiceNum());

		updateInvoice(bean);
		updateInvoiceUserItem(bean);
		
	}

	private void updateInvoiceUserItem(InvoiceBean bean) {
		CreateOrUpdateInvoiceUserItemRequest request = new CreateOrUpdateInvoiceUserItemRequest();

		// Populate the request
		//
		request.setEmpId(bean.getEmpId());
		request.setBillingRate(bean.getUnitPrice());
		request.setHours(bean.getQtyInvoiced());
		request.setTotal(bean.getAmount());
		request.setInvoiceNumber(bean.getInvoiceNum());

		invoiceService.createOrUpdateInvoiceUserItem(request);

	}

	private void updateInvoice(InvoiceBean bean) {
		CreateOrUpdateInvoiceRequest request = new CreateOrUpdateInvoiceRequest();

		// Populate the request
		//
		request.setEndDate(bean.getEndDate());
		request.setPoNumber(bean.getPoNum());
		request.setInvoiceNumber(bean.getInvoiceNum());
		request.setStartDate(bean.getStartDate());

		invoiceService.createOrUpdateInvoice(request);

	}
}
