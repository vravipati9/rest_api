package com.supplier;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientProperties;

/**
 *
 * @author airhacks.com
 */
@Path("/")
public class AsyncResource {


    @GET
    @Path("async")
    public String get() {
        return this.doSomeWork();
    }

    @GET
    @Path("asyncbg")
    public void get(@Suspended AsyncResponse response) {
    	Supplier<String> supplier = this::doSomeWork;
    	Consumer<String> consumer = response::resume;
    	// It uses fork/join framework
    	CompletableFuture.supplyAsync(supplier).thenAccept(consumer);
    	
    	// if we pass ManagedExecutorService as argument to supplyAsyn, we can control then number of
    	// threads passing from server
        //response.resume(this.doSomeWork());
    }

    
    String doSomeWork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "+" + System.currentTimeMillis();
    }

}