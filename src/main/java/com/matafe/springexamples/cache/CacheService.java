package com.matafe.springexamples.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Cache Service
 * 
 * @author Mauricio Tavares Ferraz
 */
@Service
public class CacheService
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CacheService.class);

	@Caching(evict =
	{ @CacheEvict(value = "itensCache", allEntries = true),
			@CacheEvict(value = "otherCache", allEntries = true) })
	public void cleanAll()
	{
		LOGGER.debug("Clean all caches");
		return;
	}

}
