package com.ping_client;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("pingNetwork")
public class PingNetworkResource {
	
	PingNetworkService service = new PingNetworkService();
	
	@GET
	public String message() {
		return service.message();
	}
}
