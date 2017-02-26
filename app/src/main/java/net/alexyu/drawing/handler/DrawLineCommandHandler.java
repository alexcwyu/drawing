package net.alexyu.drawing.handler;

import net.alexyu.drawing.command.DrawLineCommand;
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


        if (command.point1.x == command.point2.x) {
            IntStream.range(Math.min(command.point1.y, command.point2.y), Math.max(command.point1.y, command.point2.y) + 1)
                    .forEach(y -> builder.updateCanvas(newPoint(command.point1.x, y), Canvas.LINE));
        } else if (command.point1.y == command.point2.y) {
            IntStream.range(Math.min(command.point1.x, command.point2.x), Math.max(command.point1.x, command.point2.x) + 1)
                    .forEach(x -> builder.updateCanvas(newPoint(x, command.point1.y), Canvas.LINE));
        } else {
            throw new CommandExecutionException("only Horizontal or vertical lines are supported");
        }

        context.updateCanvas(builder.build());
    }


}
