package org.age.mag.hazelcast.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

@SuppressWarnings("rawtypes")
public class MessageListenerImpl implements MessageListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String channelName;

    public MessageListenerImpl(String name) {
        channelName = name;
    }

    @Override
    public void onMessage(Message message) {
        log.info("channel: " + channelName + " " + message.toString());
        
    }

}
