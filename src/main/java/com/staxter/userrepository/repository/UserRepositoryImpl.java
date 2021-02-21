package com.staxter.userrepository.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.staxter.userrepository.exception.UserAlreadyExistsException;
import com.staxter.userrepository.models.User;

/**
 * This class is responsible for all the operation to the repository 
 * @author srake
 *
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private static Map<String,User> userMap = new HashMap<String,User>();
	
	private static Integer userId = 0;
	
	@Value("${user.id.prefix}")
	private String isPrefix;

	/**
	 * This methods creates a new user in the repository , In this case we are using the static map as respo
	 * @param user to be created
	 * @return returns updated user 
	 * @throws UserAlreadyExistsException
	 */
	@Override
	public User createUser(User user) throws UserAlreadyExistsException {

		if (userMap.containsKey(user.getUserName())) {
			throw new UserAlreadyExistsException("A user with the given username already exists");
		} else {
			user.setId(generateId());
			userMap.put(user.getUserName(), user);
		}

		return user;
	}
	
	
	/**
	 * This method generates a Unique userId with a string prefix 
	 * @return userId
	 */
	private String generateId() {
		return isPrefix.concat((++userId).toString());
	}


	/**
	 * This methods to find user entity based on the username
	 * @return user 
	 */
	@Override
	public User findUser(String userName) {
		return userMap.get(userName);
	}

}
