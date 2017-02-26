package net.alexyu.drawing.exception;

/**
 * Created by alex on 2/26/17.
 */
public class CommandExecutionException extends RuntimeException {

    public CommandExecutionException(String message) {
        super(message);
    }

    public CommandExecutionException(String message, Throwable t) {
        super(message, t);
    }

}
