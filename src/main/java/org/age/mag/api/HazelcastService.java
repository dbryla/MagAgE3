package org.age.mag.api;

import java.util.LinkedList;

/*import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
*/
import org.age.mag.hazelcast.ClusterService;
import org.age.mag.hazelcast.dto.ClusterDTO;
import org.age.mag.hazelcast.dto.NodeDTO;
import org.age.mag.server.Guard;

//FIXME: to be deleted 
//@Path("/")
public class HazelcastService {

	private ClusterService service = new ClusterService();
	private Guard guard = new Guard();

	/*@GET
	@Path("nodes")
	@Produces(MediaType.APPLICATION_JSON)
	public LinkedList<NodeDTO> getNodes() {
		if (guard.isConnected()) {
			return service.getNodes();
		}
		return null;
	}

	@GET
	@Path("cluster")
	@Produces(MediaType.APPLICATION_JSON)
	public ClusterDTO getCluster() {
		if (guard.isConnected()) {
			return service.getCluster();
		}
		return null;
	}*/

}
