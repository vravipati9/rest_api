package com.supplier;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("processors")
public class ProcessorsResource {

    @POST
    @Path("beautification")
    public String process(String input) {
    	System.out.println("*************Beautification*****************");
        return "+" + input + "+";
    }

}