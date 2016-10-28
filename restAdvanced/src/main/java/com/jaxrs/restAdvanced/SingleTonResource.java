package com.jaxrs.restAdvanced;


import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("singleton")
@Singleton
public class SingleTonResource {

	private int count = 0;
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessage() {
		count++;
		return "It works! The method was called "+count+" times";
	}
}
