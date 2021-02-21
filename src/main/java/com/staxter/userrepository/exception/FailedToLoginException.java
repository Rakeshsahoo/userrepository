/**
 * 
 */
package com.staxter.userrepository.exception;

/**
 * @author srake
 *
 */
public class FailedToLoginException extends RuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3773448604058306814L;
	
	private String errorMsg;
	
	public FailedToLoginException(String errorMsg)
	{
		this.errorMsg = errorMsg;	
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

}
