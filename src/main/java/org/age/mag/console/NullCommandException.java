package org.age.mag.console;

@SuppressWarnings("serial")
/**
 * Exception which inform that command name wasn't given.
 *
 */
public class NullCommandException extends Exception {
    @SuppressWarnings("unused")
    private String detailMessage = "Command name can not be null.";

}
