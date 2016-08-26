package com.airhacks.messaging.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("messages")
public class MessagesResource {

    @GET
    public String getMessage() {
        //Slow down application
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hey Duke " + System.currentTimeMillis();
    }

    @POST
    public void message(String message) {
        System.out.println("Received message= " + message);
    }
}
