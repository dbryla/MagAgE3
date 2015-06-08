package org.age.mag.hazelcast.listeners;

import org.age.mag.hazelcast.ClusterManager;
import org.age.services.worker.internal.ComputationState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.MapEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryEvictedListener;
import com.hazelcast.map.listener.EntryMergedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import com.hazelcast.map.listener.MapClearedListener;
import com.hazelcast.map.listener.MapEvictedListener;

@SuppressWarnings("rawtypes")
public class WorkerStateListener implements MapClearedListener,
		MapEvictedListener, EntryAddedListener, EntryEvictedListener,
		EntryRemovedListener, EntryMergedListener, EntryUpdatedListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void entryUpdated(EntryEvent event) {
		log.info("{} {} {}", event.getEventType(), event.getValue(), event.getKey());
		ClusterManager.addWorkerState((String) event.getKey(), (ComputationState) event.getValue());
	}

	@Override
	public void entryMerged(EntryEvent event) {
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
    public void entryAdded(EntryEvent event) {
        log.info("{} {} {}", event.getEventType(), event.getValue(), event.getKey());
        ClusterManager.addWorkerState((String) event.getKey(), (ComputationState) event.getValue());
    }

	@Override
	public void mapEvicted(MapEvent event) {
		log.info(event.toString());

	}

	@Override
	public void mapCleared(MapEvent event) {
		log.info(event.toString());

	}

}
