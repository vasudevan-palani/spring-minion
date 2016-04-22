package com.minion;

import com.minion.model.InvoiceUser;
import com.minion.service.bean.InvoiceBean;
import com.minion.service.bean.InvoiceUserBean;

public class Mapper {

	public static void map(){
		
	}

	public static void map(InvoiceUser invoiceUser, InvoiceUserBean invoiceUserBean) {
		invoiceUserBean.setBillingRate(invoiceUser.getBillingRate());
		invoiceUserBean.setUserId(invoiceUser.getUserId());
		invoiceUserBean.setHours(invoiceUser.getHours());
		invoiceUserBean.setTotal(invoiceUser.getTotal());
		invoiceUserBean.setInvoiceId(invoiceUser.getInvoiceId());
		invoiceUserBean.setId(invoiceUser.getId());
	}

	public static void map(InvoiceBean invoiceBean, com.minion.model.Invoice invoice) {
		invoice.setId(invoiceBean.getId());
		invoice.setInvoiceDate(invoiceBean.getInvoiceDate());
		invoice.setStartDate(invoiceBean.getStartDate());
		invoice.setEndDate(invoiceBean.getEndDate());
		invoice.setInvoiceId(invoiceBean.getInvoiceId());
		invoice.setPoId(invoiceBean.getPoId());
		invoice.setProjectId(invoiceBean.getProjectId());
		invoice.setStatus(invoiceBean.getStatusId());
		invoice.setTotal(invoiceBean.getTotal());
		
	}

	public static void map(InvoiceUserBean invoiceUserBean, InvoiceUser invoiceUser) {
		invoiceUser.setBillingRate(invoiceUserBean.getBillingRate());
		invoiceUser.setUserId(invoiceUserBean.getUserId());
		invoiceUser.setHours(invoiceUserBean.getHours());
		invoiceUser.setTotal(invoiceUserBean.getTotal());
		invoiceUser.setInvoiceId(invoiceUserBean.getInvoiceId());
		invoiceUser.setId(invoiceUserBean.getId());
	}
}
