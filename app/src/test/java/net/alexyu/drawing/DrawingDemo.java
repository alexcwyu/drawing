package net.alexyu.drawing;

import net.alexyu.drawing.model.DrawingContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by alex on 2/27/17.
 */
public class DrawingDemo {


    private DrawingContext context;
    private CommandExecutor executor;


    @Before
    public void setup() {

        context = new DrawingContext();
        executor = new CommandExecutor(context);
    }

    @Test
    public void testDrawing() {

        executor.execute("C 20 4");

        String expected = "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        assertEquals(DrawingContext.State.Running, context.getState());
        assertEquals(expected, context.getCanvas().get().toString());


        executor.execute("L 1 2 6 2");

        expected = "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        assertEquals(DrawingContext.State.Running, context.getState());
        assertEquals(expected, context.getCanvas().get().toString());


        executor.execute("L 6 3 6 4");

        expected = "----------------------\n" +
                "|                    |\n" +
                "|xxxxxx              |\n" +
                "|     x              |\n" +
                "|     x              |\n" +
                "----------------------\n";

        assertEquals(DrawingContext.State.Running, context.getState());
        assertEquals(expected, context.getCanvas().get().toString());


        executor.execute("R 14 1 18 3");

        expected = "----------------------\n" +
                "|             xxxxx  |\n" +
                "|xxxxxx       x   x  |\n" +
                "|     x       xxxxx  |\n" +
                "|     x              |\n" +
                "----------------------\n";

        assertEquals(DrawingContext.State.Running, context.getState());
        assertEquals(expected, context.getCanvas().get().toString());


        executor.execute("B 10 3 o");

        expected = "----------------------\n" +
                "|oooooooooooooxxxxxoo|\n" +
                "|xxxxxxooooooox   xoo|\n" +
                "|     xoooooooxxxxxoo|\n" +
                "|     xoooooooooooooo|\n" +
                "----------------------\n";

        assertEquals(DrawingContext.State.Running, context.getState());
        assertEquals(expected, context.getCanvas().get().toString());


        executor.execute("Q");
        assertEquals(DrawingContext.State.Stop, context.getState());

    }


}
