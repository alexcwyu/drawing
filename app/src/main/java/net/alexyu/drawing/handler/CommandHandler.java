package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.Command;
import net.alexyu.drawing.exception.CommandExecutionException;

/**
 * Created by alex on 2/26/17.
 */
public interface CommandHandler<T extends Command> {

    void on(T command) throws CommandExecutionException;
}
