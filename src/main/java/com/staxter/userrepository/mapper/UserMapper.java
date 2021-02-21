/**
 * 
 */
package com.staxter.userrepository.mapper;

import org.springframework.stereotype.Component;

import com.staxter.userrepository.models.User;
import com.staxter.userrepository.models.ResponseUser;

/**
 * @author srake
 *
 */
@Component
public class UserMapper {
	
	/**
	 * This methods maps response from user entity to response user entity
	 * 
	 * @param user
	 * @return responseUser
	 */
	public ResponseUser getResponseUser(User user) {
		ResponseUser responseUser = new ResponseUser();
		responseUser.setFirstName(user.getFirstName());
		responseUser.setLastName(user.getLastName());
		responseUser.setUserId(user.getId());
		responseUser.setUserName(user.getUserName());
		
		return responseUser;
		
	}

}
