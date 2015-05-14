package org.age.mag.hazelcast;

import java.util.Collection;

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

import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.ReplicatedMap;

final class EventsObserver {

    private static HazelcastInstance client;

    private EventsObserver() {
    }

    public static void start(HazelcastInstance client) {
        EventsObserver.client = client;
        ClusterService.createCluster(client.getName());
        collectInitalData();
        addAnotherListeners();
    }

    private static void addAnotherListeners() {
        client.getCluster().addMembershipListener(new MembershipListenerImpl());
        client.getLifecycleService().addLifecycleListener(new LifecycleListenerImpl());
        client.getPartitionService().addPartitionLostListener(new PartitionLostListenerImpl());
        client.addDistributedObjectListener(new DistributedObjectListenerImpl());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static void collectInitalData() {
        ClusterService.setMembers(client.getCluster().getMembers());

        Collection<DistributedObject> instances = client.getDistributedObjects();
        for (DistributedObject instance : instances) {
            switch (instance.getServiceName()) {
            case "hz:impl:mapService":
                switch (((IMap<?, ?>) instance).getName()) {
                case "worker/state":
                    ((IMap<String, ComputationState>) instance).entrySet().forEach(
                            entry -> ClusterService.addWorkerState(entry.getKey(), entry.getValue()));
                    ((IMap<String, ComputationState>) instance).addEntryListener(new WorkerStateListener(), true);
                    break;
                case "status/map":
                    ((IMap<String, Status>) instance).entrySet().forEach(
                            entry -> ClusterService.addNodeStatus(entry.getKey(), entry.getValue()));
                    ((IMap<String, Status>) instance).addEntryListener(new NodeStatusListener(), true);
                    break;
                case "discovery/members":
                    ((IMap<String, NodeDescriptor>) instance).entrySet().forEach(
                            entry -> ClusterService.addNodeDescriptor(entry.getKey(), entry.getValue()));
                    ((IMap<String, Status>) instance).addEntryListener(new NodeDescriptorListener(), true);
                    break;
                case "topology/config":
                    ClusterService.setMaster(((IMap<String, String>) instance).get("master"));
                    ClusterService.setTopologyGraph(((IMap<String, UnmodifiableDirectedGraph>) instance)
                            .get("topologyGraph"));
                    ClusterService.setTopologyType(((IMap<String, String>) instance).get("topologyType"));
                    ((IMap<String, ?>) instance).addEntryListener(new TopologyConfigListener(), true);
                    break;
                default:
                    System.out.println(((IMap<?, ?>) instance).getName()); // TODO:
                                                                           // logger
                }
                break;
            case "hz:impl:topicService":
                ((ITopic<?>) instance).addMessageListener(new MessageListenerImpl(((ITopic<?>) instance).getName()));
                break;
            case "hz:impl:replicatedMapService":
                ((ReplicatedMap<?, ?>) instance).addEntryListener(new WorkerConfigListener());
                break;
            default:
                System.out.println(instance.getServiceName()); // TODO: logger
            }

        }
    }
}
