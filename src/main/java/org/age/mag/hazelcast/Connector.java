package org.age.mag.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceNotActiveException;

public final class Connector {
	private final static Connector instance = new Connector();
	private HazelcastInstance client;
	private boolean connected;

	private Connector() {}

	public static Connector getInstance() {
		return instance;
	}

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

	public boolean isConnected() {
		if (connected) {
			try {
				//TODO: log that this client is connected
				client.getName();
				return connected;
			} catch (HazelcastInstanceNotActiveException e) {
				// Ignore exception when there is no active nodes
			}
		}
		connected = false;
		return connected;
	}

	public HazelcastInstance getClient() throws ClientIsNotConnectedException {
		if (isConnected()) {
			return client;
		}
		throw new ClientIsNotConnectedException();
	}

}
