package net.alexyu.drawing.handler;

import net.alexyu.drawing.algo.Searcher;
import net.alexyu.drawing.command.BucketFillCommand;
import net.alexyu.drawing.exception.CommandExecutionException;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.DrawingContext;
import net.alexyu.drawing.model.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static net.alexyu.drawing.model.Point.newPoint;

/**
 * Created by alex on 2/26/17.
 */
public class BucketFillCommandHandler implements CommandHandler<BucketFillCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketFillCommandHandler.class);

    private final DrawingContext context;
    private final Searcher searcher;

    public BucketFillCommandHandler(DrawingContext context, Searcher searcher) {
        this.context = context;
        this.searcher = searcher;
    }

    @Override
    public void on(BucketFillCommand command) {

        if (!context.getCanvas().isPresent()) {
            throw new CommandExecutionException("Canvas is not created yet, please create canvas first");
        }

        Canvas.Builder builder = context.getCanvas().get().toBuilder();

        builder.checkBound(command.point);

        Set<Point> matched = searcher.searchMatchedNeighbour(builder, command.point);

        matched.forEach(pt -> builder.updateCanvas(newPoint(pt.x, pt.y), command.c));

        context.updateCanvas(builder.build());
    }
}
