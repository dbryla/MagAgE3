package org.age.mag.console;

import java.util.Arrays;


public final class CommandInstance {

	private StringBuilder command = new StringBuilder();
	
	public CommandInstance(String... commands) {
		Arrays.asList(commands).forEach(command::append);
	}
	
	public String getCommand() {
		return command.toString();
	}
	
}
