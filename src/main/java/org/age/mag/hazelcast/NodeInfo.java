package org.age.mag.hazelcast;

import org.age.services.identity.NodeDescriptor;
import org.age.services.status.Status;
import org.age.services.worker.internal.ComputationState;

/**
 * Class representing information about node or satellite determined by
 * satellite flag.
 *
 */
public final class NodeInfo {

    String id;
    boolean satellite = false;

    /**
     * Create new instance
     * 
     * @param id
     *            of node
     * @param isSatellite
     *            if true given instance will be representing satellite instead
     *            of node.
     */
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
        return (satellite ? "Satellite " : "Node ") + id;
    }

    /**
     * Method returns information if node or satellite is fully initialized.
     * 
     * @return information if node is ready for read or not
     */
    public boolean isReady() {
        return descriptor != null && id != null
                && ((status != null && workerState != null) || satellite);
    }
}
