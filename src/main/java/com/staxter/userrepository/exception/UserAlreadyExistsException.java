package com.staxter.userrepository.exception;

/**
 * @author srake
 *
 */
public class UserAlreadyExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4662637742092261534L;
	
	private String errorMsg ;
	
	public UserAlreadyExistsException(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	

}
