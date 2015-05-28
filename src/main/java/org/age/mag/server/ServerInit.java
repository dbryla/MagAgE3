package org.age.mag.server;

import org.age.mag.api.HazelcastService;
import org.age.mag.hazelcast.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerInit {
	private static final Logger log = LoggerFactory.getLogger(ServerInit.class);

	public static void main(String[] args) {;
		
		// Configure server
		Server jettyServer = new Server(8080);
		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed(true);
		resource_handler.setWelcomeFiles(new String[] { "index.html" });

		resource_handler.setResourceBase("src/main/resources");

		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/rest/");
		ServletHolder jerseyServlet = context.addServlet(
				org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		jerseyServlet.setInitParameter(
				"jersey.config.server.provider.classnames",
				HazelcastService.class.getCanonicalName());
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { resource_handler, context });
		jettyServer.setHandler(handlers);

		// Start server and hazelcast connector
		try {
			jettyServer.start();
			Connector con = Connector.getInstance();
			con.connect();
			jettyServer.join();
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			jettyServer.destroy();
		}
	}

}
