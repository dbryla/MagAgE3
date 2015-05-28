package org.age.mag.hazelcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceNotActiveException;

/**
 * Class for connecting to HazelcastInstance
 * 
 *
 */
public final class Connector {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final static Connector instance = new Connector();
    private HazelcastInstance client;
    private boolean connected;

    private Connector() {
    }

    /**
     * 
     * @return singleton
     */
    public static Connector getInstance() {
        return instance;
    }

    /**
     * Method which tries to connect to one of existing Hazelcast instance
     */
    public void connect() {
        try {
            client = HazelcastClient.newHazelcastClient();
            connected = true;
        } catch (IllegalStateException e) {
            connected = false;
        }
        if (connected) {
            EventsObserver.start(client);
        }
    }

    /**
     * Method for checking connector status
     * 
     * @return true if connector is connected to Hazelcast instance
     */
    public boolean isConnected() {
        if (connected) {
            try {
                log.info("Client " + client.getName() + " is connected.");
                return connected;
            } catch (HazelcastInstanceNotActiveException e) {
                // Ignore exception when there is no active nodes
            }
        }
        connected = false;
        return connected;
    }

}
