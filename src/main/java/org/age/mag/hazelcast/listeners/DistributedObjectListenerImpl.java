package org.age.mag.hazelcast.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.DistributedObjectEvent;
import com.hazelcast.core.DistributedObjectListener;

//FIXME: delete me if no logs

public class DistributedObjectListenerImpl implements DistributedObjectListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void distributedObjectCreated(DistributedObjectEvent event) {
		log.info(event.toString());
	}

	@Override
	public void distributedObjectDestroyed(DistributedObjectEvent event) {
		log.info(event.toString());
	}

}
