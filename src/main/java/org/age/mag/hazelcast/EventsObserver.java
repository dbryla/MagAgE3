package org.age.mag.hazelcast;

import java.util.Collection;

import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.ReplicatedMap;

public final class EventsObserver {

	private static HazelcastInstance client;

	private EventsObserver() {
	}

	public static void start(HazelcastInstance client) {
		EventsObserver.client = client;
		collectInitalData();
		addListeners();
	}

	private static void addListeners() {
		// TODO Auto-generated method stub
		
	}

	private static void collectInitalData() {
		Collection<DistributedObject> instances = client
				.getDistributedObjects();
		for (DistributedObject instance : instances) {
			switch(instance.getServiceName()) {
				case "hz:impl:mapService":
					switch(((IMap<?, ?>) instance).getName()) {
						case "worker/state":
								//Cluster.setWorkerState();
					}
					System.out.println(((IMap<?, ?>) instance).getName());
					System.out.println(((IMap<?, ?>) instance).keySet());
					System.out.println(((IMap<?, ?>) instance).values());
					break;
				case "hz:impl:topicService":
					System.out.println(((ITopic<?>) instance).getName());
					break;
				case "hz:impl:replicatedMapService":
					System.out.println(((ReplicatedMap<?, ?>) instance).getName());
					break;
				default:
					System.out.println(instance.getServiceName());
			}
			
		}
	}
}
