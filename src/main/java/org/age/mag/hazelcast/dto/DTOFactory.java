package org.age.mag.hazelcast.dto;

import java.util.List;

import org.age.mag.hazelcast.ClusterManager;
import org.age.mag.hazelcast.NodeInfo;
import org.age.services.identity.NodeType;

public final class DTOFactory {
    private DTOFactory() {
    }

    public static NodeDTO createNode(NodeInfo node) {
        NodeDTO dto = new NodeDTO();
        dto.setId(node.getId());
        if (node.getDescriptor() != null) {
            dto.setType(node.getDescriptor().type().toString());
            if (!dto.type.equals(NodeType.SATELLITE.toString())) {
                dto.setWorkerState(node.getWorkerState().toString());
                List<Throwable> errors = node.getStatus().errors();
                dto.setErrors(errors.toArray(new Throwable[errors.size()]));
            }
            return dto;
        } else {
            ClusterManager.removeNode(node.getId());
            return null;
        }
    }

    public static ClusterDTO createCluster(String clientName,
            String masterNode, List<String> members) {
        ClusterDTO dto = new ClusterDTO();
        dto.setClientName(clientName);
        dto.setMasterNode(masterNode);
        dto.setMembers(members.toArray(new String[members.size()]));
        return dto;
    }
}
