package com.airhacks.overload.boundary;

import com.airhacks.porcupine.configuration.control.ExecutorConfigurator;
import com.airhacks.porcupine.execution.control.ExecutorConfiguration;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import java.util.concurrent.ThreadPoolExecutor;

@Specializes
public class AnswerThreadPoolConfigurator extends ExecutorConfigurator {

    @Inject
    Event<String> overloadChannel;


    @Override
    public ExecutorConfiguration forPipeline(String name) {
        if("answers".equalsIgnoreCase(name)) {
            return new ExecutorConfiguration.Builder().corePoolSize(2).maxPoolSize(2).queueCapacity(2)
                    .customRejectedExecutionHandler(this::broadcastMessage).build();
        }
        return super.forPipeline(name);
    }

    void broadcastMessage(Runnable r, ThreadPoolExecutor executor) {
        overloadChannel.fire("Overloaded: " + executor.toString());
    }

}
