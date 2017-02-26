package net.alexyu.drawing.algo;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import static net.alexyu.drawing.model.Point.newPoint;

/**
 * Created by alex on 2/26/17.
 */
public class LinearSearcher implements Searcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinearSearcher.class);

    @Override
    public Set<Point> searchMatchedNeighbour(Canvas.Builder canvas, Point origin) {
        Map<Point, Boolean> matched = Maps.newHashMap();
        Queue<Point> queue = Queues.newConcurrentLinkedQueue();
        if (canvas.isInBound(origin)) {
            char originColor = canvas.get(origin);
            queue.offer(origin);
            while (!queue.isEmpty()) {
                Point point = queue.poll();
                searchRecursively(canvas, originColor, point, matched, queue);
            }
        }

        return matched.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    /**
     * matching condition:
     * - is within bound
     * - is matched original color
     * - is NOT searched before
     * <p>
     * action when matched:
     * 1. add to the matched result
     * 2. get other neighbours ( 3 other direction) and NOT searched before
     * 3. recursively search them
     *
     * @param canvas
     * @param originColor
     * @param point
     * @param matched
     */
    private void searchRecursively(Canvas.Builder canvas, char originColor, Point point, Map<Point, Boolean> matched, Queue<Point> queue) {

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

            queue.offer(newPoint(point.x, point.y + 1));
            queue.offer(newPoint(point.x, point.y - 1));
            queue.offer(newPoint(point.x + 1, point.y));
            queue.offer(newPoint(point.x - 1, point.y));
        }
    }

}
