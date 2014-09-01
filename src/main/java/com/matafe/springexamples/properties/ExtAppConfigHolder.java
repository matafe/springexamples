package com.matafe.springexamples.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 * External Config Holder
 * 
 * @author Mauricio Tavares Ferraz
 */
@Component
public class ExtAppConfigHolder
{
	@Value("${audit}")
	private String audit;

	/**
	 * @return the audit
	 */
	public String getAudit()
	{
		return audit;
	}

}
