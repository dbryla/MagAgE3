package org.age.mag.api;

import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.age.mag.hazelcast.ClusterService;
import org.age.mag.hazelcast.dto.ClusterDTO;
import org.age.mag.hazelcast.dto.NodeDTO;

@Path("/")
public class HazelcastService {
	
	private ClusterService service = new ClusterService();
	
	@GET
	@Path("nodes")
	@Produces(MediaType.APPLICATION_JSON)
	public LinkedList<NodeDTO> getNodes() {
		return service.getNodes();
	}
	
	@GET
	@Path("cluster")
	@Produces(MediaType.APPLICATION_JSON)
	public ClusterDTO getCluster() {
		return service.getCluster();
	}

}
