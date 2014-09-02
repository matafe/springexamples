package com.matafe.springexamples.remoting.http.client;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matafe.springexamples.common.User;
import com.matafe.springexamples.remoting.UserService;

/**
 * <p>
 * Http Service Client Application
 * 
 * @author Mauricio Tavares Ferraz
 */
public class HttpServiceClientApp
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HttpServiceClientApp.class);

	public static void main(String[] args)
	{
		LOGGER.debug("Http Service Client Application is starting...");

		// Start the Http App Context Client
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-remoting-http-client.xml",
				HttpServiceClientApp.class))
		{
			// Remote User Service Impl is called via Http Client Application
			// Context...
			UserService userService = context
					.getBean("UserService", UserService.class);

			// New User is created...
			User user = new User();
			user.setId(1L);
			user.setName("Mauricio");

			// Create a new user
			user = userService.createUser(user);

			LOGGER.debug("User created: {} ", user);

			userService = context
					.getBean("HessianUserService", UserService.class);

			// Find the users.
			Collection<User> users = userService.findAllUsers();
			for (User u : users)
			{
				LOGGER.debug("User: {} ", u);
			}

			// Remove the user
			userService.removeUser(1L);

			LOGGER.debug("Http Service Client Application is shutdown...");
		}

	}
}
