package com.jaxrs.restAdvanced.paramConverter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("{pathParam}/pathParamResource")
public class PathParamResource {

	@PathParam("pathParam")
	private int pathParamVariable;
	@QueryParam("queryParam")
	private int queryParamVariable;
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
        return "It works. Query Param " + queryParamVariable + " Path Param  " + pathParamVariable;
    }

	

	
	
}
