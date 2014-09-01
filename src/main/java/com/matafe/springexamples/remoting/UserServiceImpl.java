package com.matafe.springexamples.remoting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.matafe.springexamples.common.User;

/**
 * <p>
 * RMI User Service Impl
 * 
 * @author Mauricio Tavares Ferraz
 */
@Service
public class UserServiceImpl implements UserService
{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	private final Map<Long, User> userData = new ConcurrentHashMap<>();

	@Override
	public User createUser(User user)
	{
		user.setCreateAt(new Date());
		userData.put(user.getId(), user);
		LOGGER.debug("User {} has been created", user);
		return user;
	}

	@Override
	public boolean removeUser(Long userId)
	{
		userData.remove(userId);
		LOGGER.debug("User with id = {} has been removed", userId);
		return true;
	}

	@Override
	public Collection<User> findAllUsers()
	{
		LOGGER.debug("Finding all users");
		return new ArrayList<>(userData.values());
	}

}
