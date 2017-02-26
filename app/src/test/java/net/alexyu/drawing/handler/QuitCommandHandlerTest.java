package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.QuitCommand;
import net.alexyu.drawing.model.DrawingContext;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 2/26/17.
 */
public class QuitCommandHandlerTest{

    protected DrawingContext context;
    protected QuitCommandHandler handler;

    @Before
    public void setup(){
        context = new DrawingContext();
        handler = new QuitCommandHandler(context);
    }

    @Test
    public void testHandle() {
        assertThat(context.getState(), is(DrawingContext.State.Running));

        handler.on(new QuitCommand());

        assertThat(context.getState(), is(DrawingContext.State.Stop));
    }
}
