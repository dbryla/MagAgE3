package org.age.mag.server;

import org.age.mag.hazelcast.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerInit { 
	private static final Logger log = LoggerFactory.getLogger(ServerInit.class);

	public static void main(String[] args) {
	    log.debug("Connecting...");
		Connector.getInstance().connect();
		log.debug("Running application.");
		SpringApplication.run(ServerInit.class, args);
	}

}
