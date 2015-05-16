package org.age.mag.hazelcast.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.partition.PartitionLostEvent;
import com.hazelcast.partition.PartitionLostListener;

public class PartitionLostListenerImpl implements PartitionLostListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public void partitionLost(PartitionLostEvent event) {
        log.info(event.toString());
    }

}
