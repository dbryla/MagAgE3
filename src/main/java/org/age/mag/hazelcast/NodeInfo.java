package org.age.mag.hazelcast;

import org.age.services.identity.NodeDescriptor;
import org.age.services.status.Status;
import org.age.services.worker.internal.ComputationState;

public final class NodeInfo {

    String id;
	boolean satellite;
	
	public NodeInfo(String id, boolean isSatellite) {
		this.id = id;
		this.satellite = isSatellite;
	}
	
	ComputationState workerState;
    Status status;
    NodeDescriptor descriptor;
    

    public String getId() {
		return id;
	}


	public boolean isSatellite() {
		return satellite;
	}


	public ComputationState getWorkerState() {
		return workerState;
	}


	public Status getStatus() {
		return status;
	}


	public NodeDescriptor getDescriptor() {
		return descriptor;
	}


	@Override
    public String toString() {
    	return "Node " + id;
    }
}
