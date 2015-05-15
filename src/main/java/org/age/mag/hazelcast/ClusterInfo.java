package org.age.mag.hazelcast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.age.services.identity.NodeDescriptor;
import org.age.services.status.Status;
import org.age.services.worker.internal.ComputationState;
import org.jgrapht.graph.UnmodifiableDirectedGraph;

import com.hazelcast.core.Member;

final class ClusterInfo {

    private ArrayList<Member> members;
    private HashMap<String, NodeInfo> nodes = new HashMap<String, NodeInfo>();
    private String topologyType;
    @SuppressWarnings("rawtypes")
    private UnmodifiableDirectedGraph graph;
    private String master;

    String getMaster() {
        return master;
    }

    void setMembers(Set<Member> members) {
        this.members = new ArrayList<Member>(members);
    }
    
    List<Member> getMembers() {
        return Collections.unmodifiableList(members);
    }
    
    void addMember(Member member) {
        members.add(member);
    }
    
    void removeMember(Member member) {
        members.remove(member);
    }

    void updateWorkerState(String id, ComputationState state) {
        NodeInfo node = getNode(id);
        node.workerState = state;
        nodes.put(id, node);
    }

    void updateNodeStatus(String id, Status status) {
        NodeInfo node = getNode(id);
        node.status = status;
        nodes.put(id, node);
    }

    void updateNodeDescriptor(String id, NodeDescriptor descriptor) {
        if (descriptor != null) {
            NodeInfo node = getNode(id);
            node.descriptor = descriptor;
            nodes.put(id, node);
        } else { // descriptor == null -> means that node was deleted
            nodes.remove(id);
        }
    }

    void setTopologyType(String type) {
        topologyType = type;
    }

    @SuppressWarnings("rawtypes")
    void setTopologyGraph(UnmodifiableDirectedGraph unmodifiableDirectedGraph) {
        this.graph = unmodifiableDirectedGraph;
    }

    void setMaster(String id) {
        this.master = id;
    }

    private NodeInfo getNode(String id) {
        if (nodes.containsKey(id)) {
            return nodes.get(id);
        } else {
            return new NodeInfo();
        }
    }
}
