package com.matafe.springexamples.task;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Simple Print Task
 * 
 * @author Mauricio Tavares Ferraz
 */
public class PrintTask implements Runnable
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PrintTask.class);

	private final String id;

	public PrintTask(final String id)
	{
		this.id = id;
	}

	@Override
	public void run()
	{
		Random r = new Random();
		int nextInt = r.nextInt(7);

		LOGGER.debug(id + " is running. waiting {} sec...", nextInt);
		try
		{
			TimeUnit.SECONDS.sleep(nextInt);
		} catch (InterruptedException e)
		{
			throw new RuntimeException(e);
		}
		LOGGER.debug(id + " done.");
	}

}
