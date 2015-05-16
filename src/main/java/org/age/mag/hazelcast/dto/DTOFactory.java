package org.age.mag.hazelcast.dto;

import java.util.List;

import org.age.services.identity.NodeType;
import org.age.services.worker.internal.ComputationState;

public final class DTOFactory {
	private DTOFactory() {
	}

	public static NodeDTO createNode(String id, NodeType type, ComputationState workerState,
			List<Throwable> errors) {
		NodeDTO dto = new NodeDTO();
		dto.setId(id);
		dto.setType(type.toString());
		dto.setWorkerState(workerState.toString());
		dto.setErrors(errors.toArray(new String[errors.size()]));
		return dto;
	}
}
