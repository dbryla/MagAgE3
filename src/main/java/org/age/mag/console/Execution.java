package org.age.mag.console;

public class Execution {
	private CommandInstance command;
	private OutputWriter output;
	
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
