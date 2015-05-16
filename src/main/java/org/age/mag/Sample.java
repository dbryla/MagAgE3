package org.age.mag;

import org.age.mag.hazelcast.ClusterService;
import org.age.mag.hazelcast.Connector;
import org.slf4j.LoggerFactory;

public class Sample {
	
	public Sample() {
		Connector con = Connector.getInstance();
    	con.connect();
    	ClusterService service = new ClusterService();
    	System.out.println(service.getClientName());
    	System.out.println(service.getMasterNode());
    	System.out.println(service.getConnectedMembers());
    	LoggerFactory.getLogger(Sample.class).debug("This will be only logged on console");

	}

    public static void main(String[] args) {
        
		Connector con = Connector.getInstance();
    	con.connect();
    	ClusterService service = new ClusterService();
    	System.out.println(service.getClientName());
    	System.out.println(service.getMasterNode());
    	System.out.println(service.getConnectedMembers());
    	LoggerFactory.getLogger(Sample.class).debug("This will be only logged on console");
    	
       /* while(true) {
        	boolean isConn = con.isConnected();
        	System.out.println(isConn);
        	if (!isConn) {
        		con.connect();
        		try {
    				Thread.sleep(2000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	} else {
        		break;
        	}
        }*/
    }

}
