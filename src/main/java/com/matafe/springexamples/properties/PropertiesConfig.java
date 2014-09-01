package com.matafe.springexamples.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * <p>
 * Message Example
 * 
 * @author Mauricio Tavares Ferraz
 */
public class PropertiesConfig
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PropertiesConfig.class);

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.setProperty("app.pwd", "s3cret");
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-properties.xml", PropertiesConfig.class))
		{
			AppConfigHolder config = context.getBean(AppConfigHolder.class);

			LOGGER.debug("App Description: {}", config.getAppDescription());
			LOGGER.debug("App Author: {}", config.getAppAuthor());

			PersistenceConfigHolder persistenceConfig = context
					.getBean(PersistenceConfigHolder.class);

			LOGGER.debug("JDBC Username: {}", persistenceConfig.getUsername());

			Environment env = context.getBean(Environment.class);

			LOGGER.debug("App Pwd: {}", env.getProperty("app.pwd"));
		}

	}

}
