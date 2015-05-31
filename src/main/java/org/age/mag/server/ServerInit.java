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
		Connector.getInstance().connect();
		SpringApplication.run(ServerInit.class, args);
	}

}
