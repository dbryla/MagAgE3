package org.age.mag.console;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * Service for executing command on AgE3 console.
 *
 */
public final class ConsoleService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Console console;

    public ConsoleService() {
        console = new Console();
    }

    /**
     * Return list of available commands to execute on console
     * 
     * @return command list
     */
    public ArrayList<Command> getCommandList() {
        return new ArrayList<Command>(console.listOfAvailableCommands());
    }

    /**
     * Execute given command in console
     * 
     * NOTE: this method won't return any output
     * 
     * @param command
     *            given command
     */
    public void executeCommand(CommandInstance command) {
        try {
            executeCommand(command, new OutputWriter());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Execute given command in console
     * 
     * @param command
     *            given command
     * @param outputWriter
     *            writer which holds output from command
     */
    public void executeCommand(CommandInstance command, OutputWriter outputWriter) {
        try {
            console.executeCommand(command, outputWriter);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
