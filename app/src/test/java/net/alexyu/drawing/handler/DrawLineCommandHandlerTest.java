package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.DrawLineCommand;
import net.alexyu.drawing.exception.CommandExecutionException;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.DrawingContext;
import org.junit.Before;
import org.junit.Test;

import static net.alexyu.drawing.model.Point.newPoint;
import static org.junit.Assert.assertEquals;

/**
 * Created by alex on 2/26/17.
 */
public class DrawLineCommandHandlerTest {

    protected DrawingContext context;
    protected DrawLineCommandHandler handler;


    @Before
    public void setup() {
        context = new DrawingContext();
        handler = new DrawLineCommandHandler(context);
    }

    @Test(expected = CommandExecutionException.class)
    public void testUnInit() {
        handler.on(new DrawLineCommand(newPoint(1, 1), newPoint(2, 2)));
    }


    @Test(expected = CommandExecutionException.class)
    public void testDiagonalLine() {
        handler.on(new DrawLineCommand(newPoint(1, 1), newPoint(2, 2)));
    }

    @Test
    public void testVerticalLine() {
        context.updateCanvas(Canvas.Builder.newBuilder(4, 4).build());
        handler.on(new DrawLineCommand(newPoint(1, 1), newPoint(1, 4)));

        String expected = "------\n"
                + "|x   |\n"
                + "|x   |\n"
                + "|x   |\n"
                + "|x   |\n"
                + "------\n";

        assertEquals(expected, context.getCanvas().get().toString());
    }

    @Test
    public void testHorizontalLine() {
        context.updateCanvas(Canvas.Builder.newBuilder(4, 4).build());
        handler.on(new DrawLineCommand(newPoint(1, 1), newPoint(4, 1)));

        String expected = "------\n"
                + "|xxxx|\n"
                + "|    |\n"
                + "|    |\n"
                + "|    |\n"
                + "------\n";

        assertEquals(expected, context.getCanvas().get().toString());
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBound() {
        context.updateCanvas(Canvas.Builder.newBuilder(4, 4).build());

        handler.on(new DrawLineCommand(newPoint(1, 1), newPoint(1, 5)));
    }

}
