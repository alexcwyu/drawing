package net.alexyu.drawing;

import com.google.common.collect.ImmutableMap;
import net.alexyu.drawing.algo.LinearSearcher;
import net.alexyu.drawing.command.*;
import net.alexyu.drawing.exception.CommandExecutionException;
import net.alexyu.drawing.exception.UnsupportedCommandException;
import net.alexyu.drawing.handler.*;
import net.alexyu.drawing.model.DrawingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static net.alexyu.drawing.model.Point.newPoint;

/**
 * Created by alex on 2/26/17.
 */
public class CommandExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandExecutor.class);

    private final Map<Class<? extends Command>, CommandHandler> handlers;
    private final DrawingContext context;

    public CommandExecutor(DrawingContext context) {
        this(context, new ImmutableMap.Builder<Class<? extends Command>, CommandHandler>()
                .put(CreateCanvasCommand.class, new CreateCanvasCommandHandler(context))
                .put(DrawRectangleCommand.class, new DrawRectangleCommandHandler(context))
                .put(DrawLineCommand.class, new DrawLineCommandHandler(context))
                .put(BucketFillCommand.class, new BucketFillCommandHandler(context, new LinearSearcher()))
                .put(QuitCommand.class, new QuitCommandHandler(context))
                .build());
    }


    public CommandExecutor(DrawingContext context, Map<Class<? extends Command>, CommandHandler> handlers) {
        this.context = context;
        this.handlers = handlers;
    }

    public Command parse(String line) throws UnsupportedCommandException {
        String[] splittedCmd = line.trim().split("\\s+");


        try {
            if (CreateCanvasCommand.COMMAND.equalsIgnoreCase(splittedCmd[0])) {
                return new CreateCanvasCommand(
                        Integer.parseInt(splittedCmd[1]),
                        Integer.parseInt(splittedCmd[2]));

            } else if (DrawLineCommand.COMMAND.equalsIgnoreCase(splittedCmd[0])) {
                return new DrawLineCommand(
                        newPoint(Integer.parseInt(splittedCmd[1]), Integer.parseInt(splittedCmd[2])),
                        newPoint(Integer.parseInt(splittedCmd[3]), Integer.parseInt(splittedCmd[4])));

            } else if (DrawRectangleCommand.COMMAND.equalsIgnoreCase(splittedCmd[0])) {
                return new DrawRectangleCommand(
                        newPoint(Integer.parseInt(splittedCmd[1]), Integer.parseInt(splittedCmd[2])),
                        newPoint(Integer.parseInt(splittedCmd[3]), Integer.parseInt(splittedCmd[4])));
            } else if (BucketFillCommand.COMMAND.equalsIgnoreCase(splittedCmd[0])) {
                return new BucketFillCommand(
                        newPoint(Integer.parseInt(splittedCmd[1]), Integer.parseInt(splittedCmd[2])),
                        splittedCmd[3].charAt(0));
            } else if (QuitCommand.COMMAND.equalsIgnoreCase(splittedCmd[0])) {
                return new QuitCommand();
            } else {
                throw new UnsupportedCommandException("unsupported command: " + line);
            }
        } catch (Exception e) {

            throw new UnsupportedCommandException("failed to parse command: " + line, e);
        }

    }

    public void execute(String line) {
        try {
            Command command = parse(line);

            CommandHandler handler = handlers.get(command.getClass());
            handler.on(command);
        } catch (UnsupportedCommandException | CommandExecutionException | IndexOutOfBoundsException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

}
