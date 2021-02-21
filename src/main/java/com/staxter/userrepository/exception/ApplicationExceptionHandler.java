/**
 * 
 */
package com.staxter.userrepository.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.staxter.userrepository.models.ErrorModel;

/**
 * This class is responsible for handling all exception to service
 * @author srake
 *
 */
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String USER_ALREADY_EXISTS ="USER_ALREADY_EXISTS";
	
	private static final String LOGIN_FAILED ="LOGIN_FAILED";
	
	/**
	 * This method sets appropriate error to the response, when there is a UserAlreadyExistsException in the service
	 * @param ex
	 * @return responseEntity of type ErrorModel
	 */
	@ExceptionHandler(value = { UserAlreadyExistsException.class })
    public ResponseEntity<ErrorModel> handleBorrowedListSizeExceeded(UserAlreadyExistsException ex) {
		ErrorModel errorModel = new ErrorModel(USER_ALREADY_EXISTS, ex.getErrorMsg());
        return new ResponseEntity<ErrorModel>(errorModel,HttpStatus.CONFLICT);

    }
	
	/**
	 * This method sets appropriate error to the response, when there is a FailedToLoginException in the service
	 * @param ex
	 * @return responseEntity of type ErrorModel
	 */
	@ExceptionHandler(value = { FailedToLoginException.class })
    public ResponseEntity<ErrorModel> handleBookAlreadyExistException(FailedToLoginException ex) {
		ErrorModel errorModel = new ErrorModel(LOGIN_FAILED, ex.getErrorMsg());
        return new ResponseEntity<ErrorModel>(errorModel,HttpStatus.CONFLICT);

    }

}
