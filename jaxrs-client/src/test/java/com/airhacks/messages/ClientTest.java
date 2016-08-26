package com.airhacks.messages;

import org.glassfish.jersey.client.ClientProperties;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class ClientTest {

    private Client client;
    private WebTarget target;
    private WebTarget processor;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        target = this.client.target("http://localhost:8080/jaxrs/resources/messages");
        client.property(ClientProperties.CONNECT_TIMEOUT, 100);
        client.property(ClientProperties.READ_TIMEOUT, 500);
        this.processor = this.client.target("http://localhost:8080/processor/resources/processors/beautification");
    }

    @Test
    public void fetchRequest() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Supplier<String> supplier = () -> target.request().get(String.class);
        CompletableFuture.supplyAsync(supplier,pool)
                .thenApply(this::process)
                .exceptionally(this::handle)
                .thenAccept(this::consume)
                .get();
    }

    String handle(Throwable t) {
        return "sorry we're overloaded!";
    }

    void consume(String message) {
        this.target.request().post(Entity.text(message));
    }

    public String process (String input) {
        return this.processor.request().post(Entity.text(input)).readEntity(String.class);
    }
}
