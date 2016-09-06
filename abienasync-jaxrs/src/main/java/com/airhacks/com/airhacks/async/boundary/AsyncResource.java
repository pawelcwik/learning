package com.airhacks.com.airhacks.async.boundary;

import org.glassfish.jersey.client.ClientProperties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("async")
public class AsyncResource {

    @Resource
    ManagedExecutorService mes;

    @Resource(mappedName = "concurrentOrchestration")
    ManagedExecutorService orchestration;

    @GET
    public void get(@Suspended AsyncResponse response) {
        CompletableFuture.supplyAsync(this::doSomeWork,mes).thenAccept(response::resume);
    }


    String doSomeWork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncResource.class.getName()).log(Level.SEVERE,"Oh well");
        }
        return "+" + System.currentTimeMillis();
    }



    private Client client;
    private WebTarget target;
    private WebTarget processor;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
        target = this.client.target("http://localhost:8080/jaxrs/resources/messages");
        client.property(ClientProperties.CONNECT_TIMEOUT, 1000);
        client.property(ClientProperties.READ_TIMEOUT, 5000);
        this.processor = this.client.target("http://localhost:8080/processor/resources/processors/beautification");
    }

    @GET
    @Path("Orchestration")
    public void fetchMessage(@Suspended AsyncResponse response) {
        Supplier<String> supplier = () -> target.request().get(String.class);
        CompletableFuture.supplyAsync(supplier,orchestration)
                .thenApply(this::process)
                .exceptionally(this::handle)
                .thenApply(this::consume).thenAccept(response::resume);
    }

    String handle(Throwable t) {
        return "sorry we're overloaded!" + t.getMessage();
    }

    String consume(String message) {
        this.target.request().post(Entity.text(message));
        return message;
    }

    public String process (String input) {
        return this.processor.request().post(Entity.text(input)).readEntity(String.class);
    }
}
