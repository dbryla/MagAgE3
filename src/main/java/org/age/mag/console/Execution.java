package org.age.mag.console;

/**
 * Represents execution of command. This class holds output from command if any
 * exists.
 *
 */
public class Execution {
    private CommandInstance command;
    private OutputWriter output;

    /**
     * Create new instance.
     * 
     * @param command
     *            executed command
     * @param output
     *            OutputWriter instance for gathering output
     */
    public Execution(CommandInstance command, OutputWriter output) {
        this.command = command;
        this.output = output;
    }

    public CommandInstance getCommand() {
        return command;
    }

    public void setCommand(CommandInstance command) {
        this.command = command;
    }

    public OutputWriter getOutput() {
        return output;
    }

    public void setOutput(OutputWriter output) {
        this.output = output;
    }

}
