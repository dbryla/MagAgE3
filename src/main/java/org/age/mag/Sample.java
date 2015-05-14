package org.age.mag;

import java.util.Collection;

import org.age.mag.hazelcast.ClientIsNotConnectedException;
import org.age.mag.hazelcast.ClusterService;
import org.age.mag.hazelcast.Connector;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ITopic;

public class Sample {

    public static void main(String[] args) {
        
    	Connector con = Connector.getInstance();
    	con.connect();
    	ClusterService service = new ClusterService();
    	System.out.println(service.getClientName());
    	System.out.println(service.getMasterNode());
    	System.out.println(service.getConnectedMembers());
    	
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
