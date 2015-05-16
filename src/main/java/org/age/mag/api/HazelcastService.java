package org.age.mag.api;

import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.age.mag.hazelcast.ClusterService;
import org.age.mag.hazelcast.dto.NodeDTO;

@Path("/hello")
public class HazelcastService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LinkedList<NodeDTO> getMsg() {
		ClusterService service = new ClusterService();
		return service.getNodes();
 
	}

}
