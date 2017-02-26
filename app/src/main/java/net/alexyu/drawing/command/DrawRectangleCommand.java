package net.alexyu.drawing.command;

import net.alexyu.drawing.model.Point;

/**
 * Created by alex on 2/26/17.
 */
public class DrawRectangleCommand implements Command {

    public static final String COMMAND = "R";
    public static final String ARGS = "x1 y1 x2 y2";
    public static final String DESC = "Create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character.";

    public final Point point1;
    public final Point point2;

    public DrawRectangleCommand(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
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
        return String.format("%s %d %d %d %d", COMMAND, point1.x, point1.y, point2.x, point2.y);
    }
}
