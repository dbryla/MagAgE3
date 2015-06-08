package org.age.mag.hazelcast;

import java.util.Set;

import org.age.services.identity.NodeDescriptor;
import org.age.services.identity.NodeType;
import org.age.services.status.Status;
import org.age.services.worker.internal.ComputationState;
import org.jgrapht.graph.DefaultEdge;
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
    	NodeInfo node = clusterInfo.getNode(id, descriptor.type() == NodeType.SATELLITE);
		node.descriptor = descriptor;
		clusterInfo.addNode(node);
    }

    public static void addNodeStatus(String id, Status status) {
		NodeInfo node = clusterInfo.getNode(id);
        // ugly way to change worker state to FAILED
        if (status.errors().size() == 1
                && status.errors().get(0).toString().contains("Some computation error")) { 
            // FIXME: it probably should be changed in AgE3 not here
            ClusterManager.addWorkerState(id, ComputationState.FAILED); 
        }
		node.status = status;
		clusterInfo.addNode(node);
	}

    public static void addWorkerState(String id, ComputationState state) {
		NodeInfo node = clusterInfo.getNode(id);
		node.workerState = state;
		clusterInfo.addNode(node);
    }
    
    public static void removeNode(String id) {
        clusterInfo.removeNode(id);
    }

    public static void setMaster(String id) {
        clusterInfo.setMaster(id);
    }

    public static void setTopologyGraph(UnmodifiableDirectedGraph<String, DefaultEdge> unmodifiableDirectedGraph) {
        clusterInfo.setTopologyGraph(unmodifiableDirectedGraph);
    }

    static void setTopologyType(String type) {
        clusterInfo.setTopologyType(type);
    }

    public static void addMember(Member member) {
        clusterInfo.addMember(member);   
    }
    
    public static void removeMember(Member member) {
        clusterInfo.removeMember(member);
    }

    static void setMembers(Set<Member> members) {
        clusterInfo.setMembers(members);
    }
}
