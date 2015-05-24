package org.age.mag.console;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleWriter implements Runnable {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ConsoleReader reader;

    ConsoleWriter(ConsoleReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        ConsoleRunner.setReady();
        while (true) {
            try {
                log.debug("I will be waiting for you...");
                synchronized (getLock()) {
                    getLock().wait();
                }
                log.debug("Execute task.");
                reader.setOutput(new StringBuilder());
                ConsoleRunner.getInput().write(TaskManager.getCommand() + System.lineSeparator());
                ConsoleRunner.getInput().flush();
                Thread.sleep(100); // Give a little time for reader to read command.
                log.debug("Wait for reader.");
                while (!reader.done()) { // FIXME: ...
                    Thread.sleep(100);
                }
                log.debug("Return output.");
                TaskManager.setOutput(reader.getOutput().toString()); //FIXME: This doesn't seems to works too...
            } catch (InterruptedException | IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    private static final Object lock = new Object();
    
    static Object getLock() {
        return lock;
    }

}
