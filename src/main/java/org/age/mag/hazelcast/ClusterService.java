package org.age.mag.hazelcast;

import java.util.LinkedList;

import org.age.mag.hazelcast.dto.ClusterDTO;
import org.age.mag.hazelcast.dto.DTOFactory;
import org.age.mag.hazelcast.dto.NodeDTO;

public final class ClusterService {

	private ClusterInfo clusterInfo;

	public ClusterService() {
		clusterInfo = ClusterManager.getClusterInfo();
	}

	/**
	 * 
	 * @return connected client name
	 */
	public String getClientName() {
		return ClusterManager.getName();
	}

	/**
	 * Method returns connected members to a cluster.
	 * 
	 * @return list with string representation of each connected member
	 */
	public LinkedList<String> getConnectedMembers() {
		LinkedList<String> members = new LinkedList<String>();
		clusterInfo.getMembers().forEach(
				member -> members.add(member.getSocketAddress().toString()));
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

	/**
	 * Method returns nodes information.
	 * 
	 * @return list with nodes DTO
	 */
	public LinkedList<NodeDTO> getNodes() {
		LinkedList<NodeDTO> nodes = new LinkedList<NodeDTO>();
		clusterInfo.getNodes().forEach(
				node -> nodes.add(DTOFactory.createNode(node.id,
						node.descriptor.type(), node.workerState,
						node.status.errors())));
		return nodes;
	}
	
	/**
	 * Method returns cluster information.
	 * 
	 * @return ClusterDTO
	 */
	public ClusterDTO getCluster() {
		return DTOFactory.createCluster(getClientName(), getMasterNode(), getConnectedMembers());
	}
}
