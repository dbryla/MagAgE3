package org.age.mag.hazelcast;

import java.util.Collection;
import java.util.Set;

import org.age.mag.hazelcast.listeners.DistributedObjectListenerImpl;
import org.age.mag.hazelcast.listeners.LifecycleListenerImpl;
import org.age.mag.hazelcast.listeners.MembershipListenerImpl;
import org.age.mag.hazelcast.listeners.MessageListenerImpl;
import org.age.mag.hazelcast.listeners.NodeDescriptorListener;
import org.age.mag.hazelcast.listeners.NodeStatusListener;
import org.age.mag.hazelcast.listeners.PartitionLostListenerImpl;
import org.age.mag.hazelcast.listeners.TopologyConfigListener;
import org.age.mag.hazelcast.listeners.WorkerConfigListener;
import org.age.mag.hazelcast.listeners.WorkerStateListener;
import org.age.services.identity.NodeDescriptor;
import org.age.services.status.Status;
import org.age.services.worker.internal.ComputationState;
import org.jgrapht.graph.UnmodifiableDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Member;
import com.hazelcast.core.ReplicatedMap;

/**
 * Class for gathering information at instance startup and starting listeners
 * for all available services
 *
 */
public final class EventsObserver {

    private static final Logger log = LoggerFactory.getLogger(EventsObserver.class);
    private static HazelcastInstance client;

    private EventsObserver() {
    }

    /**
     * Starts information gather. <br>
     * 
     * <b>NOTE:</b> This method should be called only once.
     * Double execution of method can create duplicates of node information.
     * 
     * @param client
     *            instance of hazelcast for which connector is connected
     */
    public static void start(HazelcastInstance client) {
        log.info("Start getting data from hazelcast.");
        EventsObserver.client = client;
        ClusterManager.createCluster(client.getName());
        collectInitalData();
        addAnotherListeners();
        
    }

    private static void addAnotherListeners() {
        client.getCluster().addMembershipListener(new MembershipListenerImpl());
        client.getLifecycleService().addLifecycleListener(new LifecycleListenerImpl());
        client.getPartitionService().addPartitionLostListener(new PartitionLostListenerImpl());
        client.addDistributedObjectListener(new DistributedObjectListenerImpl());
    }


    private static void collectInitalData() {
        Set<Member> members = client.getCluster().getMembers();
        log.debug(members.toString());
        ClusterManager.setMembers(members);

        Collection<DistributedObject> instances = client.getDistributedObjects();
        for (DistributedObject instance : instances) {
            handleInstance(instance);
        }
    }

    /**
     * Method which handles data from instance and starts needed listeners.
     * 
     * @param instance
     *            of <code>DistributedObject</code>
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void handleInstance(DistributedObject instance) {

        switch (instance.getServiceName()) {
        case "hz:impl:mapService":
            ((IMap<?, ?>) instance).entrySet().forEach(x -> log.info("{} - {}", ((IMap<?, ?>) instance).getName(), x.toString()));
            switch (((IMap<?, ?>) instance).getName()) {
            case "worker/state":
                ((IMap<String, ComputationState>) instance).entrySet().forEach(
                        entry -> ClusterManager.addWorkerState(entry.getKey(), entry.getValue()));
                ((IMap<String, ComputationState>) instance).addEntryListener(new WorkerStateListener(), true);
                break;
            case "status/map":
                ((IMap<String, Status>) instance).entrySet().forEach(
                        entry -> ClusterManager.addNodeStatus(entry.getKey(), entry.getValue()));
                ((IMap<String, Status>) instance).addEntryListener(new NodeStatusListener(), true);
                break;
            case "discovery/members":
                ((IMap<String, NodeDescriptor>) instance).entrySet().forEach(
                        entry -> ClusterManager.addNodeDescriptor(entry.getKey(), entry.getValue()));
                ((IMap<String, Status>) instance).addEntryListener(new NodeDescriptorListener(), true);
                break;
            case "topology/config":
                ClusterManager.setMaster(((IMap<String, String>) instance).get("master"));
                ClusterManager.setTopologyGraph(((IMap<String, UnmodifiableDirectedGraph>) instance)
                        .get("topologyGraph"));
                ClusterManager.setTopologyType(((IMap<String, String>) instance).get("topologyType"));
                ((IMap<String, ?>) instance).addEntryListener(new TopologyConfigListener(), true);
                break;
            default:
                log.info("Received unknown map: " + ((IMap<?, ?>) instance).getName());
            }
            break;
        case "hz:impl:topicService":
            log.info("Registered listener for channel: {}", ((ITopic<?>) instance).getName());
            ((ITopic<?>) instance).addMessageListener(new MessageListenerImpl());
            break;
        case "hz:impl:replicatedMapService":
            ((ReplicatedMap<?, ?>) instance).entrySet().forEach(x -> log.info("{} - {}", ((ReplicatedMap<?, ?>) instance).getName(), x.toString()));
            ((ReplicatedMap<?, ?>) instance).addEntryListener(new WorkerConfigListener());
            break;
        default:
            log.info("Received unknown distributed object: " + instance.getServiceName());
        }
    
        
    }
}
