package org.age.mag.hazelcast.listeners;

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
public class NodeStatusListener implements MapClearedListener, MapEvictedListener, EntryAddedListener,
EntryEvictedListener, EntryRemovedListener, EntryMergedListener, EntryUpdatedListener {

    @Override
    public void entryUpdated(EntryEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void entryMerged(EntryEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void entryRemoved(EntryEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void entryEvicted(EntryEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void entryAdded(EntryEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mapEvicted(MapEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mapCleared(MapEvent event) {
        // TODO Auto-generated method stub
        
    }

}
