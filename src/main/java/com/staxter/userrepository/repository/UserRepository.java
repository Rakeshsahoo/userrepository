/**
 * 
 */
package com.staxter.userrepository.repository;

import org.springframework.stereotype.Component;

import com.staxter.userrepository.exception.UserAlreadyExistsException;
import com.staxter.userrepository.models.User;

/**
 * @author srake
 *
 */

@Component
public interface UserRepository {
	
	User createUser( User user ) throws UserAlreadyExistsException;
	
	User findUser(String userName);

}
