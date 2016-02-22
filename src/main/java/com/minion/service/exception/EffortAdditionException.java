package com.minion.service.exception;

import com.minion.service.MinionServiceException;

public class EffortAdditionException extends MinionServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EffortAdditionException(String errorCode,String errorMsg) {
		super(errorCode,errorMsg);
	}

}
