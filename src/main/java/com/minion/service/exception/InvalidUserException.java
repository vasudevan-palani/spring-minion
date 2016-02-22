package com.minion.service.exception;

import com.minion.service.MinionServiceException;

public class InvalidUserException extends MinionServiceException {

	public InvalidUserException(String code, String msg) {
		super(code,msg);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
