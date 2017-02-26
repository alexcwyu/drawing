package net.alexyu.drawing.command;

/**
 * Created by alex on 2/26/17.
 */
public class DrawLineCommand implements Command {

    public static final String COMMAND = "L";
    public static final String ARGS = "x1 y1 x2 y2";
    public static final String DESC = "Create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character.";

    public final int x1;
    public final int y1;
    public final int x2;
    public final int y2;

    public DrawLineCommand(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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
        return String.format("%s %d %d %d %d", COMMAND, x1, y1, x2, y2);
    }
}
