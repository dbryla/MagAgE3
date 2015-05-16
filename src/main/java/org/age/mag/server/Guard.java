package org.age.mag.server;

import org.age.mag.hazelcast.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Guard {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public boolean isConnected(){
		Connector connector = Connector.getInstance();
		boolean isConn = connector.isConnected();
    	if (!isConn) {
    		connector.connect();
    		try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			}
    		return connector.isConnected();
    	} else{
    		return true;
    	}
	}

}
