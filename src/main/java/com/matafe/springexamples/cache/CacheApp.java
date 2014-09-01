package com.matafe.springexamples.cache;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Cache Example
 * 
 * @author Mauricio Tavares Ferraz
 */
public class CacheApp
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext-cache.xml", CacheApp.class))
		{
			MyService myService = context.getBean(MyService.class);
			CacheService cacheService = context.getBean(CacheService.class);

			myService.findById(1);
			myService.findById(1);
			myService.findById(1);
			myService.findById(2);
			myService.findById(2);
			System.out.println();
			myService.findByIdUsingCache(1);
			myService.findByIdUsingCache(1);
			myService.findByIdUsingCache(1);
			myService.findByIdUsingCache(2);
			myService.findByIdUsingCache(2);
			System.out.println();
			myService.add(3);
			myService.findByIdUsingCache(1);
			System.out.println();
			cacheService.cleanAll();
			myService.findByIdUsingCache(1);
		}

	}

}
