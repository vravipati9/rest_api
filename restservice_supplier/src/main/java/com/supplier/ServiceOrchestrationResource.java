package com.supplier;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
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
import org.glassfish.jersey.server.ManagedAsyncExecutor;

/**
 *
 * @author airhacks.com
 */
@Path("/")
public class ServiceOrchestrationResource {

	@Resource
	ManagedExecutorService mes;
	
	private Client client;
    private WebTarget tut;
    private WebTarget processor;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
        client.property(ClientProperties.CONNECT_TIMEOUT, 100);
        client.property(ClientProperties.READ_TIMEOUT, 5000);
        this.tut = this.client.target("http://localhost:8080/restservice_supplier/webapi/messages");
        this.processor = this.client.target("http://localhost:8080/restservice_supplier/webapi/processors/beautification");
    }
    
    /*
     * Hit below URL to demonstrate orchestration between different services
     * fetching from MessageReosurce and processing to ProcessorsResource
     * http://localhost:8080/restservice_supplier/webapi/orches
     */
    @GET
    @Path("orches")
	public String fetchMessage() throws InterruptedException, ExecutionException, TimeoutException {
    	System.out.println("fetchMessages||");
    	ExecutorService pool = Executors.newFixedThreadPool(5);
    	Supplier<String> messageSupplier = () -> this.tut.request().get(String.class);
    	CompletableFuture.supplyAsync(messageSupplier, pool).
    	thenApply(this::process).
    	exceptionally(this::handle).
    	thenAccept(this::consume);
    	//.get();
    	return "+++";
	}
    
    //Calling it from asynchronically, above method throws exception becoz of the timeout we defined
    @GET
    @Path("orches1")
	public void fetchMessage1(@Suspended AsyncResponse response) throws InterruptedException, ExecutionException, TimeoutException {
    	System.out.println("fetchMessages1||");
    	ExecutorService pool = Executors.newFixedThreadPool(2);
    	Supplier<String> messageSupplier = () -> this.tut.request().get(String.class);
    	CompletableFuture.supplyAsync(messageSupplier, pool).
    	thenApply(this::process).
    	exceptionally(this::handle).
    	thenApply(this::consumeReturnStr).
    	thenAccept(response::resume);
    	//.get();
	}

    // If any exception comes this calls
    String handle(Throwable t) {
    	return "We are overloaded!";
    }
    
    String process(String input) {
    	Response response = this.processor.request().post(Entity.text(input));
    	return response.readEntity(String.class);
    }
    
    void consume(String message) {
    	System.out.println("message::"+message);
    	this.tut.request().post(Entity.text(message));
    }
    
    
    String consumeReturnStr(String message) {
    	this.tut.request().post(Entity.text(message));
    	return message;
    }
    
    
    String doSomeWork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServiceOrchestrationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "+" + System.currentTimeMillis();
    }

}