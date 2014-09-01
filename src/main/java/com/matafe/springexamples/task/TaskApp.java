package com.matafe.springexamples.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * <p>
 * Task App
 * 
 * @author Mauricio Tavares Ferraz
 */
public class TaskApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskApp.class);

	public static void main(String[] args) {

		System.out.println(TaskApp.class.getPackage().getName());
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-task.xml", TaskApp.class);

		ThreadPoolTaskExecutor taskExecutor = context.getBean("taskExecutor",
				ThreadPoolTaskExecutor.class);

		taskExecutor.execute(new PrintTask("Thread 1"));
		taskExecutor.execute(new PrintTask("Thread 2"));
		taskExecutor.execute(new PrintTask("Thread 3"));
		taskExecutor.execute(new PrintTask("Thread 4"));
		taskExecutor.execute(new PrintTask("Thread 5"));

		for (;;) {
			int count = taskExecutor.getActiveCount();
			LOGGER.debug("Active Threads : {}", count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 0) {
				taskExecutor.shutdown();
				break;
			}
		}

	}
}
