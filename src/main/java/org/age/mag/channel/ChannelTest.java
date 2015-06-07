package org.age.mag.channel;

import java.util.Arrays;
import java.util.HashSet;

import org.age.services.worker.WorkerMessage;
import org.age.services.worker.WorkerMessage.Type;
import org.age.services.worker.internal.DefaultWorkerService;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;


public class ChannelTest {

    public static void main(String[] args) {

        HazelcastInstance client = HazelcastClient.newHazelcastClient();
        ITopic<Object> workerTopic = client.getTopic(DefaultWorkerService.CHANNEL_NAME);
        //String a[] = {"1c0bbcd2-2f7c-46db-80ba-d3ee21e9c315"};
        //workerTopic.publish(WorkerMessage.createWithoutPayload(Type.STOP_COMPUTATION, new HashSet<String>(Arrays.asList(a))));
        workerTopic.publish(WorkerMessage.createBroadcastWithoutPayload(Type.STOP_COMPUTATION));
    }

}
