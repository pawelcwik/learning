package com.airhacks.emergency.boundary;

import javax.enterprise.event.Observes;

/**
 * Created by SG0945938 on 9/9/2016.
 */
public class OverloadHandler {

    public void onOverload(@Observes String message) {
        System.err.println("!!!!" + message);
    }
}
