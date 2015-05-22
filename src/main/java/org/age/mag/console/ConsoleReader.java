package org.age.mag.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;

import static java.util.Objects.nonNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleReader implements Runnable {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private BufferedReader reader;

	private boolean started;

	private StringBuilder output;

	public ConsoleReader(BufferedReader reader) {
		this.reader = reader;
		output = new StringBuilder();
	}

	@Override
	public void run() {
		while (true) {
			String text;
			try {
				if (!started) {
					while (true) {
						while (!reader.ready()) {
						}
						char[] buf = new char[256];
						reader.read(buf);
						text = new String(buf);
						log.debug(text);
						if (text.startsWith("AgE>")) {
							break;
						}
					}
					started = true;
					// start writer
				}
				text = reader.readLine();
				output.append(text);
				log.debug(text);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}

}
