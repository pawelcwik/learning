package com.airhacks.overload.boundary;

import javax.ejb.Stateless;

/**
 * Created by SG0945938 on 9/9/2016.
 */
@Stateless
public class AnswerFinder {

    public String find() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "42 - " + System.currentTimeMillis();
    }
}
