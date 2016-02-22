package com.minion.service.exception;

import com.minion.service.MinionServiceException;

public class AddAllocationException extends MinionServiceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddAllocationException(String code,String msg) {
		super(code,msg);
	}

}
