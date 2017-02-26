package net.alexyu.drawing.command;

/**
 * Created by alex on 2/26/17.
 */
public class QuitCommand implements Command {

    public static final String COMMAND = "Q";
    public static final String DESC = "Quit the program";


    @Override
    public String getCommandName() {
        return COMMAND;
    }

    @Override
    public String getUsage() {
        return String.format("%s : %s", COMMAND, DESC);
    }

    @Override
    public String getDescription() {
        return DESC;
    }


    @Override
    public String toString() {
        return COMMAND;
    }


}
