package com.supplier;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("messages")
public class MessageResource {

  
    @GET
    public String message() {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return "Hey dude;";
    }

    @POST
    public void message(String message) {
        System.out.println("message = " + message);
    }

}