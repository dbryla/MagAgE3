package org.age.mag.hazelcast.dto;

/**
 * Simple data transfer object representing only essential information about
 * node.
 *
 */
public class NodeDTO {
	String id;
	String type;
	String workerState;
	Throwable[] errors;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWorkerState() {
		return workerState;
	}
	public void setWorkerState(String workerState) {
		this.workerState = workerState;
	}
	public Throwable[] getErrors() {
		return errors;
	}
	public void setErrors(Throwable[] errors) {
		this.errors = errors;
	}
	
}
