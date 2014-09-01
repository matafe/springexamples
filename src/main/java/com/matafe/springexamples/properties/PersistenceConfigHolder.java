package com.matafe.springexamples.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Persitence Config Holder
 * 
 * @author Mauricio Tavares Ferraz
 */
@Component
public class PersistenceConfigHolder
{
	@Value("${jdbc.db.username}")
	private String username;

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("PersistenceConfigHolder [username=").append(username)
				.append("]");
		return builder.toString();
	}

}
