package org.age.mag.server;

import org.age.mag.hazelcast.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Checks if connector is actually connected.
 *
 */
public class Guard {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Method for checking connection, if connector isn't actually connected,
     * guard tries to connect.
     * 
     * @return if connector is connected
     */
    public boolean isConnected() {
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
        } else {
            return true;
        }
    }

}
