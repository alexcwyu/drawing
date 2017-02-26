package net.alexyu.drawing.algo;

import com.google.common.collect.Maps;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;

import static net.alexyu.drawing.model.Point.newPoint;

/**
 * Created by alex on 2/26/17.
 * <p>
 * Recursive search with ForkJoin Pool
 * for big canvas (> 20w x 20h), please increase the Max stack size by <b>-Xss</b> agrs
 */
public class FJPSearcher implements Searcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(FJPSearcher.class);
    ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

    @Override
    public Set<Point> searchMatchedNeighbour(Canvas.Builder canvas, Point origin) {
        Map<Point, Boolean> matched = Maps.newConcurrentMap();

        if (canvas.isInBound(origin)) {
            char originColor = canvas.get(origin);
            forkJoinPool.invoke(new SearchAction(canvas, originColor, origin, matched));
        }

        return matched.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    class SearchAction extends RecursiveAction {

        private final Canvas.Builder canvas;
        private final char originColor;
        private final Point point;
        private final Map<Point, Boolean> matched;


        SearchAction(Canvas.Builder canvas, char originColor, Point point, Map<Point, Boolean> matched) {
            this.canvas = canvas;
            this.originColor = originColor;
            this.point = point;
            this.matched = matched;
        }


        public void compute() {
            if (matched.containsKey(point)) {
                LOGGER.trace("{} searched already", point);
            } else if (!canvas.isInBound(point)) {
                LOGGER.trace("{} out of bound", point);
                matched.put(point, false);
            } else if (canvas.get(point) != originColor) {
                LOGGER.trace("{} mismatched", point);
                matched.put(point, false);
            } else {
                LOGGER.trace("{} matched", point);
                matched.put(point, true);

                invokeAll(new SearchAction(canvas, originColor, newPoint(point.x, point.y + 1), matched),
                        new SearchAction(canvas, originColor, newPoint(point.x, point.y - 1), matched),
                        new SearchAction(canvas, originColor, newPoint(point.x + 1, point.y), matched),
                        new SearchAction(canvas, originColor, newPoint(point.x - 1, point.y), matched));

            }

        }

    }
}
