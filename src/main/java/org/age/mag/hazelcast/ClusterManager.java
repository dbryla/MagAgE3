package org.age.mag.hazelcast;

import java.util.Set;

import org.age.services.identity.NodeDescriptor;
import org.age.services.status.Status;
import org.age.services.worker.internal.ComputationState;
import org.jgrapht.graph.UnmodifiableDirectedGraph;

import com.hazelcast.core.Member;

public final class ClusterManager {
    
    private ClusterManager() {}

    private static String clientName;
    private static ClusterInfo clusterInfo;
    
    static String getName() {
        return clientName;
    }
    
    static ClusterInfo getClusterInfo() {
        return clusterInfo;
    }

    static void createCluster(String clientName) {
        ClusterManager.clientName = clientName;
        clusterInfo = new ClusterInfo();
    }

    public static void addNodeDescriptor(String id, NodeDescriptor descriptor) {
        clusterInfo.updateNodeDescriptor(id, descriptor);
    }

    public static void addNodeStatus(String id, Status status) {
        clusterInfo.updateNodeStatus(id, status);
    }

    static void addWorkerState(String id, ComputationState state) {
        clusterInfo.updateWorkerState(id, state);
    }

    public static void setMaster(String id) {
        clusterInfo.setMaster(id);
    }

    static void setMembers(Set<Member> members) {
        clusterInfo.setMembers(members);
    }

    @SuppressWarnings("rawtypes")
    public static void setTopologyGraph(UnmodifiableDirectedGraph unmodifiableDirectedGraph) {
        clusterInfo.setTopologyGraph(unmodifiableDirectedGraph);
    }

    static void setTopologyType(String type) {
        clusterInfo.setTopologyType(type);
    }

    public static void removeNode(String id) {
        clusterInfo.removeNode(id);
    }

    public static void removeMember(Member member) {
        clusterInfo.removeMember(member);
    }

    public static void addMember(Member member) {
        clusterInfo.addMember(member);
        
    }
}
