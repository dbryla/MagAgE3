package org.age.mag.hazelcast.listeners;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

public class MessageListenerImpl implements MessageListener {

    private String channelName;

    public MessageListenerImpl(String name) {
        channelName = name;
    }

    @Override
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
        
    }

}
