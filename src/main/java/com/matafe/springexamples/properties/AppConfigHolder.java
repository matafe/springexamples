package com.matafe.springexamples.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Config Holder
 * 
 * @author Mauricio Tavares Ferraz
 */
@Component
public class AppConfigHolder
{
	@Value("${application.description:MyApp}")
	private String appDescription;

	@Value("${application.author:matafe}")
	private String appAuthor;

	/**
	 * @return the appDescription
	 */
	public String getAppDescription()
	{
		return appDescription;
	}

	/**
	 * @return the appAuthor
	 */
	public String getAppAuthor()
	{
		return appAuthor;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("AppConfigHolder [appDescription=")
				.append(appDescription).append(", appAuthor=")
				.append(appAuthor).append("]");
		return builder.toString();
	}

}
