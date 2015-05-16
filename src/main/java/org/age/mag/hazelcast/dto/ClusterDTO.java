package org.age.mag.hazelcast.dto;

public class ClusterDTO {
	
	String masterNode;
	String clientName;
	String[] members;
	public String getMasterNode() {
		return masterNode;
	}
	public void setMasterNode(String masterNode) {
		this.masterNode = masterNode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String[] getMembers() {
		return members;
	}
	public void setMembers(String[] members) {
		this.members = members;
	}
}
