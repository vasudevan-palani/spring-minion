package com.minion.service.exception;

import com.minion.service.MinionServiceException;

public class DuplicatePurchaseOrderException extends MinionServiceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatePurchaseOrderException(String code,String msg) {
		super(code,msg);
	}

}
