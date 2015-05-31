package org.age.mag.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for holding output from AgE3 console commands. To get output from
 * command use <code>getOutput()</code>.
 *
 */
public class OutputWriter extends PrintWriter {
	private final Logger log = LoggerFactory
			.getLogger(OutputWriter.class);
    private LinkedList<String> outputList;

    public OutputWriter() throws FileNotFoundException {
        super(new File("log", "console.log"));
        outputList = new LinkedList<String>();
    }

    @Override
    public void println(String x) {
        super.write(x + System.lineSeparator());
        log.debug(x);
        outputList.add(x);
    }

    public LinkedList<String> getOutput() {
        return outputList;
    }

}