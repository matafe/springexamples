package com.matafe.springexamples.properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * <p>
 * Main Properties Configuration
 * 
 * @author Mauricio Tavares Ferraz
 */
public class MainPropertiesConfigApp
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MainPropertiesConfigApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.setProperty("app.pwd", "s3cret");

		String extFilePath = createTempPropertyFile();
		LOGGER.debug("Trying: {}", extFilePath);
		System.setProperty("config.file.path", extFilePath);

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-properties.xml", MainPropertiesConfigApp.class))
		{
			AppConfigHolder config = context.getBean(AppConfigHolder.class);
			ExtAppConfigHolder extConfig = context
					.getBean(ExtAppConfigHolder.class);
			PersistenceConfigHolder persistenceConfig = context
					.getBean(PersistenceConfigHolder.class);

			LOGGER.debug("App Description: {}", config.getAppDescription());
			LOGGER.debug("App Author: {}", config.getAppAuthor());

			LOGGER.debug("Audit: {}", extConfig.getAudit());

			LOGGER.debug("JDBC Username: {}", persistenceConfig.getUsername());

			Environment env = context.getBean(Environment.class);
			LOGGER.debug("App Pwd: {}", env.getProperty("app.pwd"));

		}

	}

	private static String createTempPropertyFile()
	{
		try
		{
			String tmpDir = System.getProperty("java.io.tmpdir");
			Properties properties = new Properties();
			properties.setProperty("audit", "false");
			properties.setProperty("now",
					String.valueOf(System.currentTimeMillis()));
			File file = new File(tmpDir, "app-config.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "Spring Example Ext App Config");
			fileOut.close();
			return file.getAbsolutePath();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;

	}
}
