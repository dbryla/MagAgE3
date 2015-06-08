package org.age.mag.hazelcast.listeners;

import org.age.mag.hazelcast.ClusterManager;
import org.jgrapht.graph.UnmodifiableDirectedGraph;
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
public class TopologyConfigListener implements MapClearedListener, MapEvictedListener, EntryAddedListener,
        EntryEvictedListener, EntryRemovedListener, EntryMergedListener, EntryUpdatedListener {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void entryUpdated(EntryEvent event) {
        if (!(event.getOldValue()).equals(event.getValue())) {
            switch ((String) event.getKey()) {
            case "master":
                String master = (String) event.getValue();
                log.info("Setting new master node {}", master);
                ClusterManager.setMaster(master);
                break;
            case "topologyGraph":
                UnmodifiableDirectedGraph graph = (UnmodifiableDirectedGraph) event.getValue();
                log.info("Setting new graph {}", graph);
                ClusterManager.setTopologyGraph(graph);
                break;
            default:
                log.debug("If this ever happens, we'll need to implement next case.");
            }
        }
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
        log.info(event.toString());

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
