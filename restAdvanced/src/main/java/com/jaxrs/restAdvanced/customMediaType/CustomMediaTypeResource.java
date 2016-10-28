package com.jaxrs.restAdvanced.customMediaType;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("customMediaType")
public class CustomMediaTypeResource {

	@GET
    @Produces({MediaType.TEXT_PLAIN,"text/shortDate"})
    public Date testMethod() {
		return Calendar.getInstance().getTime();
    }
}
