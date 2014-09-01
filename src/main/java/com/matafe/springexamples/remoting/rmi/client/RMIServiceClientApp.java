package com.matafe.springexamples.remoting.rmi.client;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matafe.springexamples.common.User;
import com.matafe.springexamples.remoting.UserService;

/**
 * <p>
 * RMI Service Client Application
 * 
 * @author Mauricio Tavares Ferraz
 */
public class RMIServiceClientApp
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RMIServiceClientApp.class);

	public static void main(String[] args)
	{
		LOGGER.debug("RMI Service Client Application is starting...");

		// Start the RMI App Context Client
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-remoting-rmi-client.xml",
				RMIServiceClientApp.class))
		{
			// Remote User Service Impl is called via RMI Client Application
			// Context...
			UserService userService = (UserService) context
					.getBean("UserService");

			// New User is created...
			User user = new User();
			user.setId(1L);
			user.setName("Mauricio");

			// Create a new user
			user = userService.createUser(user);

			LOGGER.debug("User created: {} ", user);

			// Find the users.
			Collection<User> users = userService.findAllUsers();
			for (User u : users)
			{
				LOGGER.debug("User: {} ", u);
			}

			// Remove the user
			userService.removeUser(1L);

			LOGGER.debug("RMI Service Client Application is shutdown...");
		}

	}
}
