package net.alexyu.drawing.command;

/**
 * Created by alex on 2/26/17.
 */
public class DrawRectangleCommand implements Command{

    public static final String COMMAND = "R";
    public static final String ARGS = "x1 y1 x2 y2";
    public static final String DESC = "Create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character.";

    public final int x1;
    public final int y1;
    public final int x2;
    public final int y2;

    public DrawRectangleCommand(int x1, int y1, int x2, int y2) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrawRectangleCommand that = (DrawRectangleCommand) o;

        if (x1 != that.x1) return false;
        if (y1 != that.y1) return false;
        if (x2 != that.x2) return false;
        return y2 == that.y2;
    }

    @Override
    public int hashCode() {
        int result = x1;
        result = 31 * result + y1;
        result = 31 * result + x2;
        result = 31 * result + y2;
        return result;
    }
}
