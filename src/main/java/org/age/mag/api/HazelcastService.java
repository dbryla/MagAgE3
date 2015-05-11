package org.age.mag.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HazelcastService {
	
	@GET
	public Response getMsg() {
		String output = "Jersey say";
		return Response.status(200).entity(output).build();
 
	}

}
