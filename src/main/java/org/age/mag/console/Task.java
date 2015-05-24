package org.age.mag.console;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            return TaskManager.execute(instance);
        }
    });
    
    private final Task instance;
    private final String command;

    Task(String command) {
        this.command = command;
        this.instance = this;
    }

    String getCommand() {
        return command;
    }

    public String getOutput() {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            String message = e.getMessage();
            log.error(message);
            return message;
        }
    }

    void execute() {
        future.run();
    }
}
