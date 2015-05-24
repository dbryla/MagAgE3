package org.age.mag.console;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleReader implements Runnable {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private BufferedInputStream reader;

	private boolean started;

	private StringBuilder output;

	public ConsoleReader(BufferedInputStream inputStreamReader) {
		this.reader = inputStreamReader;
		setOutput(new StringBuilder());
	}

	@Override
	public void run() {
		while (true) {
			String text;
			try {
				if (!started) {
					while (true) {
						byte[] buf = new byte[256];
						reader.read(buf);
						text = new String(buf);
						log.debug(text);
						if (text.contains("AgE>")) {
						    log.debug("Console initialized.");
							break;
						}
					}
					started = true;
					new Thread(new ConsoleWriter(this)).start();
				}
				log.debug("Trying to read some input.");
				byte[] buf = new byte[256];
                reader.read(buf);
                text = new String(buf);
				getOutput().append(text);
				log.debug(text);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}

    public StringBuilder getOutput() {
        return output;
    }

    public void setOutput(StringBuilder output) {
        this.output = output;
    }

    public boolean done() {
        try {
            log.debug("Check available bytes to read from stream.");
            int available = reader.available(); //FIXME: this op blocks
            log.debug("Available bytes to read: " + available);
            return available == 0;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return false;
    }

}
