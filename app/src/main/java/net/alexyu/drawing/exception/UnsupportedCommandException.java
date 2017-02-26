package net.alexyu.drawing.exception;

/**
 * Created by alex on 2/26/17.
 */
public class UnsupportedCommandException extends RuntimeException {


    public UnsupportedCommandException(String message) {
        super(message);
    }
    public UnsupportedCommandException(String message, Throwable t) {
        super(message, t);
    }

}
