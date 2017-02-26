package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.DrawRectangleCommand;
import net.alexyu.drawing.exception.CommandExecutionException;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.DrawingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

/**
 * Created by alex on 2/26/17.
 */
public class DrawRectangleCommandHandler implements CommandHandler<DrawRectangleCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrawRectangleCommandHandler.class);

    private final DrawingContext context;

    public DrawRectangleCommandHandler(DrawingContext context) {
        this.context = context;
    }

    @Override
    public void on(DrawRectangleCommand command) {
        if (!context.getCanvas().isPresent()) {
            throw new CommandExecutionException("Canvas is not created yet, please create canvas first");
        }

        Canvas.Builder builder = context.getCanvas().get().toBuilder();

        IntStream.range(command.x1, command.x2 + 1).forEach(x -> builder.updateCanvas(x, command.y1, Canvas.LINE));
        IntStream.range(Math.min(command.y1, command.y2) + 1, Math.max(command.y1, command.y2)).forEach(y -> {
            builder.updateCanvas(command.x1, y, Canvas.LINE);
            builder.updateCanvas(command.x2, y, Canvas.LINE);
        });

        IntStream.range(command.x1, command.x2 + 1).forEach(x -> builder.updateCanvas(x, command.y2, Canvas.LINE));

        context.updateCanvas(builder.build());

    }
}
