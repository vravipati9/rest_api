package com.ping_client;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("ping")
public class PingResource {
	
	PingService service = new PingService();
	


	@GET
	public String message() {
		return service.message();
	}
}
