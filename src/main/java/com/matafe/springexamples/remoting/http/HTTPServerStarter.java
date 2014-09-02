package com.matafe.springexamples.remoting.http;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Starts the HTTP Server
 * 
 * @author Mauricio Tavares Ferraz
 */
public class HTTPServerStarter
{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HTTPServerStarter.class);

	public static void main(String[] args) throws Exception
	{
		LOGGER.debug("HTTP Service Server is starting...");

		// Start the Server
//		@SuppressWarnings("unused")
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//				"applicationContext-remoting-http.xml", HTTPServerStarter.class);

		Tomcat tomcat = new Tomcat();

		String webPort = System.getenv("http.port");
		if (webPort != null && !webPort.isEmpty())
		{
			tomcat.setPort(Integer.valueOf(webPort));
		}

		String webappDirAbsolutePath = new File("src/main/webapp")
				.getAbsolutePath();
		String baseDirAbsolutePath = new File("target")
				.getAbsolutePath();

		final String contextPath = "/SpringExamples";
		final String hostName = "localhost";

		tomcat.addWebapp(contextPath, webappDirAbsolutePath);
		tomcat.setBaseDir(baseDirAbsolutePath);
		tomcat.setHostname(hostName);

		LOGGER.info("ContextPath: "
				+ contextPath);
		LOGGER.info("Webapp Basedir: "
				+ webappDirAbsolutePath);
		LOGGER.info("Port: "
				+ tomcat.getConnector().getPort());

		tomcat.start();
		tomcat.getServer().await();

	}
}
