package org.age.mag.console;

import java.util.ArrayList;

/**
 * Class for representing available command with all parameters
 *
 */
public final class Command {

    private String cmdName;
    private ArrayList<String> options = new ArrayList<String>();
    private ArrayList<String> operations = new ArrayList<String>();

    Command(String cmd) {
        cmdName = cmd;
    }

    public String getCmdName() {
		return cmdName;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public ArrayList<String> getOperations() {
		return operations;
	}

	void addParameter(String option) {
        if (option.startsWith("--")) {
            options.add(option);
        } else {
            operations.add(option);
        }
    }

    @Override
    public String toString() {
        return "Command: " + getCmdName() + ", operations: " + operations + ", options: " + options;
    }

}
