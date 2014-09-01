package com.matafe.springexamples.remoting;

import java.util.Collection;

import com.matafe.springexamples.common.User;

/**
 * <p>
 * User Service
 * 
 * @author Mauricio Tavares Ferraz
 */
public interface UserService
{
	User createUser(User user);

	boolean removeUser(Long userId);

	Collection<User> findAllUsers();
}
