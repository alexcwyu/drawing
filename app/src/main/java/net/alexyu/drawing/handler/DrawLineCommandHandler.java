package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.DrawLineCommand;
import net.alexyu.drawing.exception.CommandExecutionException;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.DrawingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

/**
 * Created by alex on 2/26/17.
 */
public class DrawLineCommandHandler implements CommandHandler<DrawLineCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrawLineCommandHandler.class);

    private final DrawingContext context;

    public DrawLineCommandHandler(DrawingContext context) {
        this.context = context;
    }

    @Override
    public void on(DrawLineCommand command) {
        if (!context.getCanvas().isPresent()) {
            throw new CommandExecutionException("Canvas is not created yet, please create canvas first");
        }

        Canvas.Builder builder = context.getCanvas().get().toBuilder();

//        if (command.x1 == command.x2 && command.y1 == command.y2) {
//            builder.updateCanvas(command.x1, command.y1, Canvas.LINE);
//        } else
        if (command.x1 == command.x2) {
            IntStream.range(command.y1, command.x2 + 1).forEach(y -> builder.updateCanvas(command.x1, y, Canvas.LINE));
        } else if (command.y1 == command.y2) {
            IntStream.range(command.x1, command.x2 + 1).forEach(x -> builder.updateCanvas(x, command.y1, Canvas.LINE));
        } else {
            throw new CommandExecutionException("only Horizontal or vertical lines are supported");
        }

        context.updateCanvas(builder.build());
    }


}
