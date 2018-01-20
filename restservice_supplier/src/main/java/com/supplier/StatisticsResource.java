package com.supplier;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.supplier.control.MonitoringSink;

@Stateless
@Path("statistics")
public class StatisticsResource {

    @Inject
    MonitoringSink sink;

    @GET
    public JsonObject statistics() {
        return Json.createObjectBuilder().
                add("total-msg-count", sink.getMessageCount()).
                add("last-exception", sink.getLastException()).
                add("exception-count", sink.getExceptionCount()).
                build();
    }
}