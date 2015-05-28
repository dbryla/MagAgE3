package org.age.mag.example;

import java.io.PrintWriter;

import javax.inject.Named;

import org.age.console.command.BaseCommand;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.context.annotation.Scope;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * AgE3 console command created for test purposes.
 *
 */
@Named
@Scope("prototype")
@Parameters(commandNames = "magTest", commandDescription = "Run test operation for Managment Panel", optionPrefixes = "--")
public final class ManagmentTestCommand extends BaseCommand {

    private enum Operation {
        KILL("kill"), MESSAGE("message");

        private final String operationName;

        Operation(final @NonNull String operationName) {
            this.operationName = operationName;
        }

        public String operationName() {
            return operationName;
        }
    }

    @Parameter(names = "--text")
    private @MonotonicNonNull String msgText;

    public ManagmentTestCommand() {
        addHandler(Operation.KILL.operationName(), this::killServer);
        addHandler(Operation.MESSAGE.operationName(), this::displayMessage);
    }

    private void killServer(PrintWriter printWriter) {
        System.exit(0);
    }

    private void displayMessage(PrintWriter printWriter) {
        printWriter.println(msgText != null ? msgText : "Default message");
    }

}
