package org.age.mag.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.Map;

import org.age.mag.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleRunner {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final String CONSOLE = "console";
	private static final String NO_DAEMON = "--no-daemon";
	private static final String GRADLEW_COMMAND = "./gradlew";

	public void start() {
		Props.loadProperties();
		ProcessBuilder builder = new ProcessBuilder(GRADLEW_COMMAND, NO_DAEMON,
				CONSOLE);
		builder.redirectErrorStream(true);
		builder.redirectInput(Redirect.INHERIT);
		builder.directory(Props.getAgeHomeDir());
		Map<String, String> environment = builder.environment();
		if (!environment.containsKey("JAVA_HOME")) {
			environment.put("JAVA_HOME", Props.getJavaHome());
		}
		try {
			Process process = builder.start();
			new Thread(new ConsoleReader(new BufferedReader(
					new InputStreamReader(process.getInputStream())))).start();
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

	public static void main(String[] args) {
		ConsoleRunner console = new ConsoleRunner();
		console.start();
	}

}
