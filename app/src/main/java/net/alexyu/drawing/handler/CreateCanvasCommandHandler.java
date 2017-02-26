package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.CreateCanvasCommand;
import net.alexyu.drawing.exception.CommandExecutionException;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.DrawingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alex on 2/26/17.
 */
public class CreateCanvasCommandHandler implements CommandHandler<CreateCanvasCommand> {


    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCanvasCommandHandler.class);

    private final DrawingContext context;

    public CreateCanvasCommandHandler(DrawingContext context) {
        this.context = context;
    }

    @Override
    public void on(CreateCanvasCommand command) throws CommandExecutionException {

        if (context.getCanvas().isPresent()) {
            throw new CommandExecutionException("Canvas is created already");
        }
        Canvas canvas = Canvas.Builder.newBuilder(command.width, command.height).build();

        context.updateCanvas(canvas);
    }

}
