package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.DrawLineCommand;
import net.alexyu.drawing.command.DrawRectangleCommand;
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
public class DrawRectangleCommandHandlerTest {

    protected DrawingContext context;
    protected DrawRectangleCommandHandler handler;


    @Before
    public void setup() {
        context = new DrawingContext();
        handler = new DrawRectangleCommandHandler(context);
    }

    @Test(expected = CommandExecutionException.class)
    public void testUnInit() {
        handler.on(new DrawRectangleCommand(newPoint(1, 1), newPoint(2, 2)));
    }


    @Test
    public void testRect() {
        context.updateCanvas(Canvas.Builder.newBuilder(4, 4).build());
        handler.on(new DrawRectangleCommand(newPoint(1, 1), newPoint(3, 3)));

        String expected = "------\n"
                + "|xxx |\n"
                + "|x x |\n"
                + "|xxx |\n"
                + "|    |\n"
                + "------\n";

        assertEquals(expected, context.getCanvas().get().toString());


        handler.on(new DrawRectangleCommand(newPoint(2, 2), newPoint(3, 4)));
        String expected2 = "------\n"
                + "|xxx |\n"
                + "|xxx |\n"
                + "|xxx |\n"
                + "| xx |\n"
                + "------\n";

        assertEquals(expected2, context.getCanvas().get().toString());
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBound() {
        context.updateCanvas(Canvas.Builder.newBuilder(4, 4).build());

        handler.on(new DrawRectangleCommand(newPoint(1, 1), newPoint(1, 5)));
    }
}
