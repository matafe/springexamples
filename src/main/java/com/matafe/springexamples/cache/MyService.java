package com.matafe.springexamples.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * My Service
 * 
 * @author Mauricio Tavares Ferraz
 */
@Service
public class MyService
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyService.class);

	public long findById(final long id)
	{
		LOGGER.debug("Finding id = {}", id);
		return id;
	}

	@Cacheable("itensCache")
	public long findByIdUsingCache(final long id)
	{
		LOGGER.debug("Finding id = {} using cache", id);
		return id;
	}

	@CacheEvict(value = "itensCache", allEntries = true)
	public long add(final long id)
	{
		LOGGER.debug("Adding id = {} so let's evict this cache", id);
		return id;

	}

}
