package org.age.mag.console;

import java.util.ArrayList;

public final class Command {

	private String cmdName;
	private ArrayList<String> options = new ArrayList<String>();
	private ArrayList<String> operations = new ArrayList<String>();

	public Command(String cmd) {
		this.cmdName = cmd;
	}

	public void addParameter(String option) {
		if (option.startsWith("--")) {
			options.add(option);
		} else {
			operations.add(option);
		}
	}
	
	@Override
	public String toString() {
		return "Command: " + cmdName + ", operations: " + operations + ", options: " + options;
	}

}
