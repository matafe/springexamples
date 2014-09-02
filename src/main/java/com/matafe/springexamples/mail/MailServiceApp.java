package com.matafe.springexamples.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * MailServiceApp
 * 
 * @author Mauricio Tavares Ferraz
 */
public class MailServiceApp
{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MailServiceApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String password = System.getProperty("password");
		if (password == null)
		{
			throw new IllegalArgumentException(
					"Please set the Mail Sender Password");
		}
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-mail.xml", MailService.class))
		{
			LOGGER.debug("Trying to send email...");
			MailService mailService = context.getBean(MailService.class);
			mailService.sendPreConfiguredMail("Teste Message- It works!");

			LOGGER.debug("End");

		}
	}
}
