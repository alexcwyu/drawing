package net.alexyu.drawing.handler;

import net.alexyu.drawing.CommandExecutor;
import net.alexyu.drawing.command.BucketFillCommand;
import net.alexyu.drawing.exception.CommandExecutionException;
import net.alexyu.drawing.model.DrawingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alex on 2/26/17.
 */
public class BucketFillCommandHandler implements CommandHandler<BucketFillCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketFillCommandHandler.class);

    private final DrawingContext context;

    public BucketFillCommandHandler(DrawingContext context) {
        this.context = context;
    }

    @Override
    public void on(BucketFillCommand command) {

        if (!context.getCanvas().isPresent()) {
            throw new CommandExecutionException("Canvas is not created yet, please create canvas first");
        }
    }
}
