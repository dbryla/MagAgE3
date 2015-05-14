package org.age.mag.hazelcast;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.age.services.identity.NodeDescriptor;
import org.age.services.status.Status;
import org.age.services.worker.internal.ComputationState;
import org.jgrapht.graph.UnmodifiableDirectedGraph;

import com.hazelcast.core.Member;

public final class ClusterService {

    private static String clientName;
    private static ClusterInfo clusterInfo;

    static void createCluster(String clientName) {
        ClusterService.clientName = clientName;
        clusterInfo = new ClusterInfo();
    }

    static void addNodeDescriptor(String id, NodeDescriptor descriptor) {
        clusterInfo.updateNodeDescriptor(id, descriptor);
    }

    static void addNodeStatus(String id, Status status) {
        clusterInfo.updateNodeStatus(id, status);
    }

    static void addWorkerState(String id, ComputationState state) {
        clusterInfo.updateWorkerState(id, state);
    }

    static void setMaster(String id) {
        clusterInfo.setMaster(id);
    }

    static void setMembers(Set<Member> members) {
        clusterInfo.setMembers(members);
    }

    @SuppressWarnings("rawtypes")
    static void setTopologyGraph(UnmodifiableDirectedGraph unmodifiableDirectedGraph) {
        clusterInfo.setTopologyGraph(unmodifiableDirectedGraph);
    }

    static void setTopologyType(String type) {
        clusterInfo.setTopologyType(type);
    }

    /**
     * 
     * @return connected client name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Method returns connected members to a cluster.
     * 
     * @return list with string representation of each connected member
     */
    public LinkedList<String> getConnectedMembers() {
        LinkedList<String> members = new LinkedList<String>();
        clusterInfo.getMembers().forEach(member -> members.add(member.getSocketAddress().toString()));
        return members;
    }

    /**
     * Method returns master node id.
     * 
     * @return id
     */
    public String getMasterNode() {
        return clusterInfo.getMaster();
    }

}
