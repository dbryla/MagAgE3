package org.age.mag.hazelcast;

import org.age.services.identity.NodeDescriptor;
import org.age.services.status.Status;
import org.age.services.worker.internal.ComputationState;

final class NodeInfo {

    ComputationState workerState;
    Status status;
    NodeDescriptor descriptor;

}
