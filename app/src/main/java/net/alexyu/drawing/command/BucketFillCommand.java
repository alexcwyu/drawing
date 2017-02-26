package net.alexyu.drawing.command;

/**
 * Created by alex on 2/26/17.
 */
public class BucketFillCommand implements Command {
    public static final String COMMAND = "B";
    public static final String ARGS = "x y c";
    public static final String DESC = "fill the entire area connected to (x,y) with \"colour\" c. The behaviour of this is the same as that of the \"bucket fill\" tool in paint programs.";

    public final int x;
    public final int y;
    public final String c;

    public BucketFillCommand(int x, int y, String c) {
        this.x = x;
        this.y = y;
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
        return String.format("%s %d %d %s", COMMAND, x, y, c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BucketFillCommand that = (BucketFillCommand) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        return c != null ? c.equals(that.c) : that.c == null;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }
}
