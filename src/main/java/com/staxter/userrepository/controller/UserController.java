/**
 * 
 */
package com.staxter.userrepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.staxter.userrepository.exception.FailedToLoginException;
import com.staxter.userrepository.exception.UserAlreadyExistsException;
import com.staxter.userrepository.models.ResponseUser;
import com.staxter.userrepository.models.User;
import com.staxter.userrepository.service.UserRepositoryService;

/**
 * @author srake
 *
 */
@RestController
@RequestMapping("/userservice")
public class UserController {
	
	@Autowired
	UserRepositoryService userRepositoryService;
	
	
	/**
	 * This method handles the user creation
	 * @param user
	 * @return responseEntity of type ResponseUser
	 * @throws UserAlreadyExistsException
	 */
	@RequestMapping(value ="/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseUser> create(@RequestBody User user) throws UserAlreadyExistsException
	{
		return new ResponseEntity<ResponseUser>(userRepositoryService.createUser(user),HttpStatus.OK);
	}
	
	/**
	 * This method handles the login request
	 * @param user
	 * @return responseEntity of type ResponseUser
	 * @throws FailedToLoginException
	 */
	@RequestMapping(value ="/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseUser> login(@RequestBody User user) throws FailedToLoginException
	{
		return new ResponseEntity<ResponseUser>(userRepositoryService.handleLogin(user),HttpStatus.OK);	
	}
	

}
