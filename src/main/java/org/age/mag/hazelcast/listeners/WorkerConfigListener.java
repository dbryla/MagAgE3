package org.age.mag.hazelcast.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.core.MapEvent;

//FIXME: delete me if no logs

@SuppressWarnings("rawtypes")
public class WorkerConfigListener implements EntryListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public void entryAdded(EntryEvent event) {
        log.info(event.toString());

    }

    @Override
    public void entryUpdated(EntryEvent event) {
        log.info(event.toString());

    }

    @Override
    public void entryRemoved(EntryEvent event) {
        log.info(event.toString());

    }

    @Override
    public void entryEvicted(EntryEvent event) {
        log.info(event.toString());

    }

    @Override
    public void mapCleared(MapEvent event) {
        log.info(event.toString());

    }

    @Override
    public void mapEvicted(MapEvent event) {
		log.info(event.toString());

    }

}
