package org.age.mag.hazelcast.listeners;

import org.age.mag.hazelcast.ClusterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.Member;
import com.hazelcast.core.MemberAttributeEvent;
import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;

public class MembershipListenerImpl implements MembershipListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void memberAdded(MembershipEvent event) {
	    Member member = event.getMember();
		log.info("New member added {}", member);
        ClusterManager.addMember(member);

	}

	@Override
	public void memberRemoved(MembershipEvent event) {
        Member member = event.getMember();
        log.info("Member removed {}", member);
		ClusterManager.removeMember(event.getMember());
	}

	@Override
	public void memberAttributeChanged(MemberAttributeEvent event) {
		log.info(event.toString());

	}

}
