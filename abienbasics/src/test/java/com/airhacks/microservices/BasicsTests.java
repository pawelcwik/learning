package com.airhacks.microservices;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BasicsTests {


    @Test
    public void references() {
        Runnable run = this::display;
        new Thread(run).start();
    }

    void display() {
        System.out.println("hey custom duke");
    }

    public String message() {
        return "Hey Duke " + System.currentTimeMillis();
    }

    @Test
    public void callable() throws ExecutionException, InterruptedException {
        Callable<String> messageProvider = this::message;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = new ArrayList<>();
        for(int i =0; i<10;i++) {
            Future<String> futureResult = executorService.submit(messageProvider);
            futures.add(futureResult);
        }
        for(Future<String> result : futures) {
            System.out.println(result.get());
        }

    }

    @Test
    public void threadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for( int i =0;i<10000;i++) {
            Runnable run = this::display;
            Thread t = new Thread(run);
            executorService.submit(run);
        }
    }
}
