package net.alexyu.drawing.command;

/**
 * Created by alex on 2/26/17.
 */
public class CreateCanvasCommand implements Command {

    public static final String COMMAND = "C";
    public static final String ARGS = "w h";
    public static final String DESC = "Create a new canvas of width w and height h.";

    public final int width;
    public final int height;


    public CreateCanvasCommand(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String getCommandName() {
        return COMMAND;
    }

    @Override
    public String getUsage() {
        return String.format("%s %s : %s", COMMAND, ARGS, DESC);
    }

    @Override
    public String getDescription() {
        return DESC;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", COMMAND, width, height);
    }

}
