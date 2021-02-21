/**
 * 
 */
package com.staxter.userrepository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staxter.userrepository.exception.FailedToLoginException;
import com.staxter.userrepository.exception.UserAlreadyExistsException;
import com.staxter.userrepository.mapper.UserMapper;
import com.staxter.userrepository.models.ResponseUser;
import com.staxter.userrepository.models.User;
import com.staxter.userrepository.repository.UserRepository;

/**
 * @author srake
 *
 */

@Service
public class UserRepositoryService {
	
	@Autowired
	UserRepository useRepository;
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * This service method responsible for creating new user in the repository 
	 * @param user
	 * @return responseUser
	 * @throws UserAlreadyExistsException
	 */
	public ResponseUser createUser(User user) throws UserAlreadyExistsException {
		return userMapper.getResponseUser(useRepository.createUser(user));
	}
	
	/**
	 * This method takes care of user login validation
	 * @param user
	 * @return userResponse 
	 * @throws FailedToLogin
	 */
	public ResponseUser handleLogin(User user) throws FailedToLoginException {
		User existingUser = useRepository.findUser(user.getUserName());
		if(!(existingUser != null && user.getHashedPassword().equals(existingUser.getHashedPassword()))) {
			throw new FailedToLoginException("Incorrect user crendentials, Please try again");
		}
		return userMapper.getResponseUser(existingUser);
	}

}
