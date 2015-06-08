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

    private boolean notInitialized;

    public ClusterService() {
        clusterInfo = ClusterManager.getClusterInfo();
        notInitialized = clusterInfo == null;
    }

    /**
     * 
     * @return connected client name
     * @throws ClusterNotInitializedException when cluster isn't initialized
     */
    public String getClientName() throws ClusterNotInitializedException {
        if (notInitialized) {
            throw new ClusterNotInitializedException();
        }
        return ClusterManager.getName();
    }

    /**
     * Method returns connected members to a cluster.
     * 
     * @return list with string representation of each connected member
     * @throws ClusterNotInitializedException when cluster isn't initialized
     */
    public LinkedList<String> getConnectedMembers() throws ClusterNotInitializedException {
        if (notInitialized) {
            throw new ClusterNotInitializedException();
        }
        LinkedList<String> members = new LinkedList<String>();
        clusterInfo.getMembers().forEach(
                member -> members.add(member.getSocketAddress().toString()));
        return members;
    }

    /**
     * Method returns master node id.
     * 
     * @return id
     * @throws ClusterNotInitializedException when cluster isn't initialized
     */
    public String getMasterNode() throws ClusterNotInitializedException {
        if (notInitialized) {
            throw new ClusterNotInitializedException();
        }
        return clusterInfo.getMaster();
    }

    /**
     * Method returns nodes information.
     * 
     * @return list with nodes DTO
     * @throws ClusterNotInitializedException when cluster isn't initialized
     */
    public LinkedList<NodeDTO> getNodes() throws ClusterNotInitializedException {
        if (notInitialized) {
            throw new ClusterNotInitializedException();
        }
        LinkedList<NodeDTO> nodes = new LinkedList<NodeDTO>();
        Collection<NodeInfo> data = clusterInfo.getNodes();
        log.debug("Getting list of nodes: " + data);
        synchronized (data) {
            data.stream().filter(node -> node.isReady()).forEach(node -> {
                NodeDTO nodeDTO = DTOFactory.createNode(node);
                if (nodeDTO != null) {
                    nodes.add(nodeDTO);
                }
            });
        }
        return nodes;
    }

    /**
     * Method returns cluster information.
     * 
     * @return ClusterDTO
     * @throws ClusterNotInitializedException when cluster isn't initialized
     */
    public ClusterDTO getCluster() throws ClusterNotInitializedException {
        if (notInitialized) {
            throw new ClusterNotInitializedException();
        }
        return DTOFactory.createCluster(getClientName(), getMasterNode(),
                getConnectedMembers());
    }

    /**
     * Check if specific service is properly initialized
     * 
     * @return true if is not initialized
     */
    public boolean isNotInitialized() {
        return notInitialized;
    }
    /**
     * Method returns Graph information in JSON.
     * 
     * @return String
     * @throws ClusterNotInitializedException when cluster isn't initialized
     */
    public String getGraph() throws ClusterNotInitializedException {
        if (notInitialized) {
            throw new ClusterNotInitializedException();
        }
        return DTOFactory.convertGraphToJson(clusterInfo.getTopologyGraph());
    }
}
