package org.age.mag.hazelcast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.UnmodifiableDirectedGraph;

import com.hazelcast.core.Member;

final class ClusterInfo {

	private ArrayList<Member> members;
	private HashMap<String, NodeInfo> nodes = new HashMap<String, NodeInfo>();
	private String topologyType;
	private UnmodifiableDirectedGraph<String, DefaultEdge> graph;
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
	
	void addNode(NodeInfo node) {
		nodes.put(node.id, node);
	}
	
	NodeInfo getNode(String id) {
		return getNode(id, false);
	}
	
	NodeInfo getNode(String id, boolean isSatellite) {
		if (nodes.containsKey(id)) {
			return nodes.get(id);
		} else {
			return new NodeInfo(id, isSatellite); 
		}
	}

	void removeNode(String id) {
		nodes.remove(id);
	}
	
    Collection<NodeInfo> getNodes() {
        return new CopyOnWriteArrayList<NodeInfo>(nodes.values());
    }

	void setTopologyType(String type) {
		topologyType = type;
	}

	void setTopologyGraph(UnmodifiableDirectedGraph<String, DefaultEdge> unmodifiableDirectedGraph) {
		this.graph = unmodifiableDirectedGraph;
	}

	void setMaster(String id) {
		this.master = id;
	}
	
	public UnmodifiableDirectedGraph<String, DefaultEdge> getTopologyGraph() {
		return graph;
	}

}
