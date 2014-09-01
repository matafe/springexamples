package com.matafe.springexamples.concurrency;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * <p>
 * Sequencial File Size Calculator
 * 
 * @author Mauricio Tavares Ferraz
 */
public class SequencialFileSizeCalculator
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SequencialFileSizeCalculator.class);

	private long getTotalSizeOfFilesInDir(final File file)
	{
		if (file.isFile())
		{
			return file.length();
		}
		final File[] children = file.listFiles();
		long total = 0;
		if (children != null)
		{
			for (final File child : children)
			{
				total += getTotalSizeOfFilesInDir(child);
			}
		}
		return total;
	}

	public static void main(final String[] args)
	{
		if (args.length == 0)
		{
			LOGGER.debug("Usage example: SequencialFileSizeCalculator {}",
					"/opt");
		} else
		{
			SequencialFileSizeCalculator calculator = new SequencialFileSizeCalculator();
			StopWatch watch = new StopWatch();

			watch.start();
			long total = calculator.getTotalSizeOfFilesInDir(new File(args[0]));
			watch.stop();

			LOGGER.debug("Total Size: {}", total);
			LOGGER.debug("Time taken: {}", watch.getTotalTimeSeconds());

		}
	}
}
