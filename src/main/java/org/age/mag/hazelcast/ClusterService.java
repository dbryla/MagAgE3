package org.age.mag.hazelcast;

import java.util.LinkedList;

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
