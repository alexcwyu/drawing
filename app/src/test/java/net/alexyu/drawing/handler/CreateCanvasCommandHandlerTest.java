package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.CreateCanvasCommand;
import net.alexyu.drawing.exception.CommandExecutionException;
import net.alexyu.drawing.model.DrawingContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by alex on 2/26/17.
 */
public class CreateCanvasCommandHandlerTest {

    protected DrawingContext context;
    protected CreateCanvasCommandHandler handler;

    @Before
    public void setup() {
        context = new DrawingContext();
        handler = new CreateCanvasCommandHandler(context);
    }

    @Test
    public void testCreateCanvas() {
        assertFalse(context.getCanvas().isPresent());

        handler.on(new CreateCanvasCommand(4, 3));


        String expected = "------\n"
                + "|    |\n"
                + "|    |\n"
                + "|    |\n"
                + "------\n";

        assertEquals(expected, context.getCanvas().get().toString());
    }

    @Test(expected = CommandExecutionException.class)
    public void testRecreateCanvas() {
        handler.on(new CreateCanvasCommand(4, 3));
        handler.on(new CreateCanvasCommand(4, 3));
    }
}
