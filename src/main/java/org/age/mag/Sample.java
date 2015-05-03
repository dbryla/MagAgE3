package org.age.mag;

import java.util.Collection;
import java.util.Iterator;

import org.age.services.identity.NodeDescriptor;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ITopic;

public class Sample {

    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("127.0.0.1:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        System.out.println(client.getName());
        
        Collection<DistributedObject> instances =  client.getDistributedObjects();
        for(DistributedObject instance: instances){
            System.out.println(instance.getServiceName());
            if (instance instanceof IMap) {
                System.out.println(((IMap) instance).values());
            } else if (instance instanceof ITopic<?>) {
                System.out.println(((ITopic) instance).getName());
            }
        }
    }

}
