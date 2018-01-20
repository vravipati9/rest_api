package com.supplier;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("messagesejb")
public class MessageResourceEJB {
	
	@Inject
	Event<String> monitoringChannel;
	
    @GET
    public String message() {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	String message = "Hey duke!";
    	monitoringChannel.fire(message);
        return message + System.currentTimeMillis();
    }

    @POST
    public void message(String message) {
        System.out.println("message = " + message);
    }

}