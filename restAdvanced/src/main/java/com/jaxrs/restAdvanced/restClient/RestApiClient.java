package com.jaxrs.restAdvanced.restClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jaxrs.restAdvanced.messenger.model.Message;

public class RestApiClient {
	public static void main(String a[]) {
		Client client = ClientBuilder.newClient();
		String msg = client.target("http://localhost:8080/restAdvanced/webapi/myresource")
				.request(MediaType.APPLICATION_JSON) // we need json response
				.get(String.class);
		//Msg msg = response.readEntity(Msg.class);
		System.out.println(Message.class)   ;
	}
}