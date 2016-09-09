package com.airhacks.overload.boundary;

import com.airhacks.porcupine.execution.boundary.Dedicated;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Path("overload")
@Stateless
public class OverloadResource {

    @Inject
    AnswerFinder finder;

    @Inject @Dedicated
    ExecutorService answers;


    @GET
    public void answers(@Suspended AsyncResponse response) {
        response.setTimeout(2, TimeUnit.SECONDS);
        response.setTimeoutHandler(this::onTimeout);
        CompletableFuture.supplyAsync(finder::find,answers).thenAccept(response::resume);

    }

    public void onTimeout(AsyncResponse response) {
        Response status = Response.status(Response.Status.SERVICE_UNAVAILABLE).header("reason", "overloaded").build();
        response.resume(status);
    }

}
