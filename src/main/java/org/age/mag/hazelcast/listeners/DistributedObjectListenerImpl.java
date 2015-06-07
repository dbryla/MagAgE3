package org.age.mag.hazelcast.listeners;

import org.age.mag.hazelcast.EventsObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.DistributedObjectEvent;
import com.hazelcast.core.DistributedObjectListener;

public class DistributedObjectListenerImpl implements DistributedObjectListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void distributedObjectCreated(DistributedObjectEvent event) {
	    EventsObserver.handleInstance(event.getDistributedObject());
	}

	@Override
	public void distributedObjectDestroyed(DistributedObjectEvent event) {
		log.info(event.toString());
	}

}
