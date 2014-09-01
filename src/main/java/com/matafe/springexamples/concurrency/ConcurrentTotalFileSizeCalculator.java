package com.matafe.springexamples.concurrency;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * <p>
 * Description
 * 
 * @author Mauricio Tavares Ferraz
 */
public class ConcurrentTotalFileSizeCalculator {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConcurrentTotalFileSizeCalculator.class);

	class SubDirectoriesAndSize {
		final long size;
		final List<File> subDirectories;

		public SubDirectoriesAndSize(final long totalSize,
				final List<File> theSubDirs) {
			this.size = totalSize;
			this.subDirectories = Collections.unmodifiableList(theSubDirs);
		}
	}

	private SubDirectoriesAndSize getTotalAndSubDirs(final File file) {
		long total = 0;
		List<File> subDirectories = new ArrayList<File>();
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			if (children != null) {
				for (final File child : children) {
					if (child.isFile()) {
						total += child.length();
					} else {
						subDirectories.add(child);
					}
				}
			}
		}
		return new SubDirectoriesAndSize(total, subDirectories);
	}

	private long getTotalSizeOfFilesInDir(final ExecutorService service,
			final File file) throws InterruptedException, ExecutionException,
			TimeoutException {

		try {
			long total = 0;
			List<File> directories = new ArrayList<File>();
			directories.add(file);
			while (!directories.isEmpty()) {
				List<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<Future<SubDirectoriesAndSize>>();
				for (final File directory : directories) {
					partialResults.add(service
							.submit(new Callable<SubDirectoriesAndSize>() {
								public SubDirectoriesAndSize call() {
									return getTotalAndSubDirs(directory);
								}
							}));
				}

				directories.clear();

				for (final Future<SubDirectoriesAndSize> partialResultFuture : partialResults) {
					SubDirectoriesAndSize subDirectoriesAndSize = partialResultFuture
							.get(2, TimeUnit.MINUTES);
					directories.addAll(subDirectoriesAndSize.subDirectories);
					total += subDirectoriesAndSize.size;
				}
			}
			return total;
		} finally {
			service.shutdown();
		}

	}

	private long getTotalSizeOfFilesInDir(final File file)
			throws InterruptedException, ExecutionException, TimeoutException {
		ExecutorService service = Executors.newFixedThreadPool(100);
		try {
			return getTotalSizeOfFilesInDir(service, file);
		} finally {
			service.shutdown();
		}
	}

	public static void main(final String[] args) throws InterruptedException,
			ExecutionException, TimeoutException {
		if (args.length == 0) {
			LOGGER.debug("Usage example: ConcurrentTotalFileSizeCalculator {}",
					"/opt");
		} else {
			ConcurrentTotalFileSizeCalculator calculator = new ConcurrentTotalFileSizeCalculator();
			StopWatch watch = new StopWatch();

			watch.start();
			long total = calculator.getTotalSizeOfFilesInDir(new File(args[0]));
			watch.stop();

			LOGGER.debug("Total Size: {}", total);
			LOGGER.debug("Time taken: {}", watch.getTotalTimeSeconds());
		}
	}
}
