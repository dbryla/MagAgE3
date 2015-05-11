package org.age.mag.server;

import org.age.mag.api.HazelcastService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerInit {
	public static void main(String[] args) {

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

		try {
			jettyServer.start();
			jettyServer.join();
		} catch (Exception e) {
			// TODO log message
			e.printStackTrace();
		} finally {
			jettyServer.destroy();
		}
	}

}
