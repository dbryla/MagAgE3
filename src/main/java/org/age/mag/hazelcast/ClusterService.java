package org.age.mag.hazelcast;

import java.util.Collection;
import java.util.LinkedList;

import org.age.mag.hazelcast.dto.ClusterDTO;
import org.age.mag.hazelcast.dto.DTOFactory;
import org.age.mag.hazelcast.dto.NodeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * Service for getting useful information about cluster instance
 * 
 *
 */
public final class ClusterService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

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
		Collection<NodeInfo> data = clusterInfo.getNodes();
		log.debug("Getting list of nodes: " + data);
		data.forEach(node -> nodes.add(DTOFactory.createNode(node)));
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
