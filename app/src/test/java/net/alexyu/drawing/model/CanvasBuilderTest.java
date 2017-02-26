package net.alexyu.drawing.model;

import org.junit.Test;

import static net.alexyu.drawing.model.Point.newPoint;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 2/26/17.
 */
public class CanvasBuilderTest {
    @Test
    public void testBuild() {
        Canvas.Builder builder = Canvas.Builder.newBuilder(4, 3);
        Canvas canvas = builder.build();

        String expected = "------\n"
                + "|    |\n"
                + "|    |\n"
                + "|    |\n"
                + "------\n";
        assertEquals(expected, canvas.toString());

        Canvas canvas2 = canvas.toBuilder().build();

        assertEquals(canvas, canvas2);

    }

    @Test
    public void testIsInBound() {

        Canvas.Builder builder = Canvas.Builder.newBuilder(4, 3);

        assertTrue(builder.isInBound(newPoint(1,1)));
        assertTrue(builder.isInBound(newPoint(4,3)));

        assertFalse(builder.isInBound(newPoint(-1,0)));
        assertFalse(builder.isInBound(newPoint(1,0)));
        assertFalse(builder.isInBound(newPoint(4,4)));
    }

    @Test
    public void testUpdateAndGet() {

        Canvas.Builder builder = Canvas.Builder.newBuilder(4, 3);

        Point pt = newPoint(2, 2);
        assertEquals(' ', builder.get(pt));

        builder.updateCanvas(pt, 'x');
        assertEquals('x', builder.get(pt));
    }
}
