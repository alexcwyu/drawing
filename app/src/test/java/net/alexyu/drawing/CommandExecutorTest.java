package net.alexyu.drawing;

import com.google.common.collect.ImmutableMap;
import net.alexyu.drawing.CommandExecutor;
import net.alexyu.drawing.command.*;
import net.alexyu.drawing.exception.UnsupportedCommandException;
import net.alexyu.drawing.handler.CommandHandler;
import net.alexyu.drawing.handler.CreateCanvasCommandHandler;
import net.alexyu.drawing.handler.QuitCommandHandler;
import net.alexyu.drawing.model.DrawingContext;
import org.junit.BeforeClass;
import org.junit.Test;

import static net.alexyu.drawing.model.Point.newPoint;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by alex on 2/26/17.
 */
public class CommandExecutorTest {


    private static CommandExecutor executor;
    private static QuitCommandHandler quitHandler;
    private static CreateCanvasCommandHandler createHandler;

    @BeforeClass
    public static void init() {
        quitHandler = mock(QuitCommandHandler.class);
        createHandler = mock(CreateCanvasCommandHandler.class);

        executor = new CommandExecutor(new DrawingContext(), new ImmutableMap.Builder<Class<? extends Command>, CommandHandler>()
                .put(CreateCanvasCommand.class, createHandler)
                .put(QuitCommand.class, quitHandler)
                .build());
    }

    @Test
    public void testParseCreateCanvasCommand() {

        CreateCanvasCommand command = (CreateCanvasCommand) executor.parse("C 20 4");

        assertNotNull(command);
        assertEquals(4, command.height);
        assertEquals(20, command.width);
    }


    @Test
    public void testDrawLineCommand() {

        DrawLineCommand command = (DrawLineCommand) executor.parse("L 1 2 6 2");

        assertNotNull(command);
        assertEquals(newPoint(1, 2), command.point1);
        assertEquals(newPoint(6, 2), command.point2);
    }


    @Test
    public void testDrawRectangleCommand() {

        DrawRectangleCommand command = (DrawRectangleCommand) executor.parse("R 14 1 18 3");

        assertNotNull(command);
        assertEquals(newPoint(14, 1), command.point1);
        assertEquals(newPoint(18, 3), command.point2);
    }

    @Test
    public void testBucketFillCommand() {

        BucketFillCommand command = (BucketFillCommand) executor.parse("B 10 3 o");

        assertNotNull(command);
        assertEquals(newPoint(10, 3), command.point);
        assertEquals('o', command.c);
    }

    @Test
    public void testQuitCommand() {

        QuitCommand command = (QuitCommand) executor.parse("Q");

        assertNotNull(command);

    }


    @Test(expected = UnsupportedCommandException.class)
    public void testUnknownCommand() {
        executor.parse("??");
    }

    @Test
    public void testExecute() {
        executor.execute("C 20 4");

        verify(createHandler, times(1)).on(any());
        verify(quitHandler, times(0)).on(any());
    }
}
