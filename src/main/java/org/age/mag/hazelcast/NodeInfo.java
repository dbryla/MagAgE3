package org.age.mag.hazelcast;

import org.age.services.identity.NodeDescriptor;
import org.age.services.status.Status;
import org.age.services.worker.internal.ComputationState;

final class NodeInfo {

    String id;
	public NodeInfo(String id) {
		this.id = id;
	}
	
	ComputationState workerState;
    Status status;
    NodeDescriptor descriptor;

}
