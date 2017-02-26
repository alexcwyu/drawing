package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.DrawRectangleCommand;
import net.alexyu.drawing.exception.CommandExecutionException;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.DrawingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

import static net.alexyu.drawing.model.Point.newPoint;

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

        IntStream.range(Math.min(command.point1.x, command.point2.x), Math.max(command.point1.x, command.point2.x) + 1)
                .forEach(x -> builder.updateCanvas(newPoint(x, command.point1.y), Canvas.LINE));

        IntStream.range(Math.min(command.point1.y, command.point2.y) + 1, Math.max(command.point1.y, command.point2.y))
                .forEach(y -> {
                    builder.updateCanvas(newPoint(command.point1.x, y), Canvas.LINE);
                    builder.updateCanvas(newPoint(command.point2.x, y), Canvas.LINE);
                });

        IntStream.range(Math.min(command.point1.x, command.point2.x), Math.max(command.point1.x, command.point2.x) + 1)
                .forEach(x -> builder.updateCanvas(newPoint(x, command.point2.y), Canvas.LINE));

        context.updateCanvas(builder.build());

    }
}
