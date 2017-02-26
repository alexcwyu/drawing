package net.alexyu.drawing.command;

/**
 * Created by alex on 2/26/17.
 */
public interface Command {

    String getCommandName();

    String getUsage();

    String getDescription();
}
