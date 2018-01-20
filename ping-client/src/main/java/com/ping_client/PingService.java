package com.ping_client;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Stateless
public class PingService {
	private WebTarget pingTarget;
	
	@PostConstruct
	public void initClient() {
		System.out.println("*****Post Construct*****");
		Client client = ClientBuilder.newClient();
		this.pingTarget = client.target(uri());
	}
	
	//LINKEDPING_PORT_8080_TCP_ADDR=172.17.0.2
	//LINKEDPING_PORT_8080_TCP_PORT=8080
	//http://localhost:8282/ping/resources/health/start-time
	//http://172.17.0.2:8080/ping/resources/health/start-time
	private String uri() {
		String hostName = System.getenv("PING_PORT_8080_TCP_ADDR");
		String port = System.getenv("PING_PORT_8080_TCP_PORT");
		String ipAddress =  "http://"+hostName+":"+port+"/ping/resources/health/start-time";
		System.out.println(ipAddress);
		return ipAddress;
	}
	
	public String message() {
		Client client = ClientBuilder.newClient();
		this.pingTarget = client.target(uri());
		String finalRequest = this.pingTarget.request().get(String.class);
		System.out.println("******final Request****"+finalRequest);
		return finalRequest;
	}
	
	
}
