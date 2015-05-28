package org.age.mag.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Class for holding output from AgE3 console commands. To get output from
 * command use <code>getOutput()</code>.
 *
 */
public class OutputWriter extends PrintWriter {
    private StringBuilder outputBuffer;

    public OutputWriter() throws FileNotFoundException {
        super(new File("log", "console.log"));
        outputBuffer = new StringBuilder();
    }

    @Override
    public void println(String x) {
        super.println(x);
        outputBuffer.append(x + System.lineSeparator());
    }

    public String getOutput() {
        return outputBuffer.toString();
    }

}