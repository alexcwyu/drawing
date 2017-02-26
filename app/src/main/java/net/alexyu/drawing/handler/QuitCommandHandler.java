package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.QuitCommand;
import net.alexyu.drawing.model.DrawingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alex on 2/26/17.
 */
public class QuitCommandHandler implements CommandHandler<QuitCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuitCommandHandler.class);

    private final DrawingContext context;

    public QuitCommandHandler(DrawingContext context) {
        this.context = context;
    }

    @Override
    public void on(QuitCommand command) {


        context.setState(DrawingContext.State.Stop);

    }
}
