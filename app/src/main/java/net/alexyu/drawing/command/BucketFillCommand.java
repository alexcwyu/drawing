package net.alexyu.drawing.command;

import net.alexyu.drawing.model.Point;

/**
 * Created by alex on 2/26/17.
 */
public class BucketFillCommand implements Command {
    public static final String COMMAND = "B";
    public static final String ARGS = "x y c";
    public static final String DESC = "fill the entire area connected to (x,y) with \"colour\" c. The behaviour of this is the same as that of the \"bucket fill\" tool in paint programs.";

    public final Point point;
    public final char c;

    public BucketFillCommand(Point point, char c) {
        this.point = point;
        this.c = c;
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
        return String.format("%s %d %d %s", COMMAND, point.x, point.y, c);
    }

}
