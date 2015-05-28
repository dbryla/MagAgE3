package org.age.mag.console;

import java.util.Arrays;

/**
 * Command instance which is passed to console for commend execution
 *
 */
public final class CommandInstance {

    private StringBuilder command = new StringBuilder();

    /**
     * Constructor for command
     * 
     * @param commands
     *            string(s) to invoke on AgE3 console
     */
    public CommandInstance(String... commands) {
        Arrays.asList(commands).forEach(command::append);
    }

    public String getCommand() {
        return command.toString();
    }

}
