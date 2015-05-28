package org.age.mag.console;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleRunner {
    private final static Logger log = LoggerFactory.getLogger(ConsoleRunner.class);

    private static final String CONSOLE = "console";
    private static final String NO_DAEMON = "--no-daemon";
    private static final String GRADLEW_COMMAND = "./gradlew";

    private static BufferedWriter input;

    private static boolean ready;

    private ConsoleRunner() {
    }

    public static void start() {
        ProcessBuilder builder = new ProcessBuilder(GRADLEW_COMMAND, NO_DAEMON, CONSOLE);
        builder.redirectErrorStream(true);
//        builder.redirectInput(Redirect.INHERIT);
//        builder.redirectOutput(Redirect.INHERIT);
        Map<String, String> environment = builder.environment();
        try {
            Process process = builder.start();
            input = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            new Thread(new ConsoleReader(new BufferedInputStream(process.getInputStream()))).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int exitValue = process.waitFor();
                        if (0 != exitValue) {
                            log.error("Console exited with non-zero status.");
                        }
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                }
            }).start();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static BufferedWriter getInput() {
        return input;
    }

    public static void setReady() {
        ready = true;
    }

    public static boolean isReady() {
        return ready;
    }

    public static void main(String[] args) {
        ConsoleRunner.start();
        while (!ConsoleRunner.isReady()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        log.debug("Starting tasks.");
        Task task1 = TaskManager
                .scheduleTask("computation load --config agent-platform/src/main/resources/org/age/example/mas/computation.cfg");
        log.debug(task1.getOutput());
        Task task2 = TaskManager.scheduleTask("computation start");
        log.debug(task2.getOutput());
        log.debug("End.");
       TaskManager.scheduleTask("help");
    }

}
