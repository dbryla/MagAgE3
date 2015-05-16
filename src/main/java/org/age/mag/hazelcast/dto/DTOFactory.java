package org.age.mag.hazelcast.dto;

import java.util.List;

import org.age.mag.hazelcast.NodeInfo;
import org.age.services.identity.NodeType;

public final class DTOFactory {
	private DTOFactory() {
	}

	public static NodeDTO createNode(NodeInfo node) {
		NodeDTO dto = new NodeDTO();
		dto.setId(node.getId());
		dto.setType(node.getDescriptor().type().toString());
		if (!dto.type.equals(NodeType.SATELLITE.toString())) {
			dto.setWorkerState(node.getWorkerState().toString());
			List<Throwable> errors = node.getStatus().errors();
			dto.setErrors(errors.toArray(new String[errors.size()]));
		}
		return dto;
	}
}
