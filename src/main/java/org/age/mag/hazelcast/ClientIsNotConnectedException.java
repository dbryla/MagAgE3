package org.age.mag.hazelcast;

public final class ClientIsNotConnectedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Connect connector before using client.";
	}

}
