package org.age.mag.hazelcast.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleListener;

/**
 * Logs information about changes in lifecycle service.
 *
 */
public class LifecycleListenerImpl implements LifecycleListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public void stateChanged(LifecycleEvent event) {
    	log.info(event.toString());
    }

}
