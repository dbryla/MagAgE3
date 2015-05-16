package org.age.mag.hazelcast.dto;

public class NodeDTO {
	String id;
	String type;
	String workerState;
	String[] errors;
	
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
	public String[] getErrors() {
		return errors;
	}
	public void setErrors(String[] errors) {
		this.errors = errors;
	}
	
}
