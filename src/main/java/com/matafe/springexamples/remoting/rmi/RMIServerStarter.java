package com.matafe.springexamples.remoting.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Starts the RMI Server
 * 
 * @author Mauricio Tavares Ferraz
 */
public class RMIServerStarter
{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RMIServerStarter.class);
	
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		LOGGER.debug("RMI Service Server is starting...");
		
		// Start the RMI Server
		@SuppressWarnings("unused")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-remoting-rmi.xml", RMIServerStarter.class);
	}
}
