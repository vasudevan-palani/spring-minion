package com.minion.service.exception;

import com.minion.service.MinionServiceException;

public class DuplicateInvoiceException extends MinionServiceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateInvoiceException(String code,String msg) {
		super(code,msg);
	}

}
