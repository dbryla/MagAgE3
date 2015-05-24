package org.age.mag.console;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskManager {
    private final static Logger log = LoggerFactory.getLogger(TaskManager.class);

    private static String command;
    private static LinkedList<Task> tasks = new LinkedList<Task>();
    private static String output;

    public static Task scheduleTask(String command) {
        log.debug("Create task with command: " + command);
        Task task = new Task(command);
        tasks.add(task);
        task.execute();
        return task;
    }

    static String execute(Task task) throws InterruptedException {
        log.debug("I'll be waiting for my turn.");
        while (!tasks.getFirst().equals(task)) {
            Thread.sleep(100);
        }
        log.debug("End of that endless wait");
        tasks.remove();
        command = task.getCommand();
        log.debug("Notify writer.");
        synchronized (ConsoleWriter.getLock()) {
            ConsoleWriter.getLock().notify();
        }
        log.debug("I'll be waiting for output.");
        synchronized (ConsoleWriter.getLock()) {
            ConsoleWriter.getLock().wait();
        }
        log.debug("Ha got it!");
        return output;
    }

    static String getCommand() {
        return command;
    }

    static void setOutput(String string) {
        output = string;
        synchronized (ConsoleWriter.getLock()) {
            ConsoleWriter.getLock().notify();
        }
    }

}
