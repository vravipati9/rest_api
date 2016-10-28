package com.jaxrs.restAdvanced;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jaxrs.restAdvanced.restClient.Msg;

@Path("myresource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MyResource {

	@GET
	public Msg getMessage() {
		Msg m = new Msg(12, "MONE", "RSR");
		return m;
	}
	
	
/*	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Msg testMethod() {
       // return "It works. Query Param " + queryParamVariable + " Path Param  " + pathParamVariable;
		return new Msg(32, "New message created", "veera");
    }*/

	
	
}
