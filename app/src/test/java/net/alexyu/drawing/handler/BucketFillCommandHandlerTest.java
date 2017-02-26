package net.alexyu.drawing.handler;

import com.google.common.collect.Sets;
import net.alexyu.drawing.algo.Searcher;
import net.alexyu.drawing.command.BucketFillCommand;
import net.alexyu.drawing.exception.CommandExecutionException;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.DrawingContext;
import org.junit.Before;
import org.junit.Test;

import static net.alexyu.drawing.model.Point.newPoint;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by alex on 2/26/17.
 */
public class BucketFillCommandHandlerTest {

    protected DrawingContext context;
    protected BucketFillCommandHandler handler;


    @Before
    public void setup() {
        context = new DrawingContext();

        Searcher searcher = mock(Searcher.class);
        when(searcher.searchMatchedNeighbour(any(), eq(newPoint(1, 1))))
                .thenReturn(Sets.newHashSet(newPoint(1, 1), newPoint(1, 2), newPoint(1, 3), newPoint(2, 1), newPoint(2, 2), newPoint(3, 1)));

        handler = new BucketFillCommandHandler(context, searcher);
    }


    @Test(expected = CommandExecutionException.class)
    public void testUnInit() {
        handler.on(new BucketFillCommand(newPoint(1, 1), 'o'));
    }

    @Test
    public void testHandle() {
        context.updateCanvas(Canvas.Builder.newBuilder(4, 4).build());

        handler.on(new BucketFillCommand(newPoint(1, 1), 'o'));

        String expected = "------\n"
                + "|ooo |\n"
                + "|oo  |\n"
                + "|o   |\n"
                + "|    |\n"
                + "------\n";

        assertEquals(expected, context.getCanvas().get().toString());
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBound() {
        context.updateCanvas(Canvas.Builder.newBuilder(4, 4).build());

        handler.on(new BucketFillCommand(newPoint(4, 5), 'o'));
    }


}
