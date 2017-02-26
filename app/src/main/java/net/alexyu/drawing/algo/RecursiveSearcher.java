package net.alexyu.drawing.algo;

import com.google.common.collect.Maps;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static net.alexyu.drawing.model.Point.newPoint;

/**
 * Created by alex on 2/26/17.
 * <p>
 * for big canvas (> 50w x 50h), please increase the Max stack size by <b>-Xss</b> agrs
 */
public class RecursiveSearcher implements Searcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecursiveSearcher.class);

    @Override
    public Set<Point> searchMatchedNeighbour(Canvas.Builder canvas, Point origin) {
        Map<Point, Boolean> matched = Maps.newHashMap();
        if (canvas.isInBound(origin)) {
            char originColor = canvas.get(origin);
            searchRecursively(canvas, originColor, origin, matched);
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
    private void searchRecursively(Canvas.Builder canvas, char originColor, Point point, Map<Point, Boolean> matched) {

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

            searchRecursively(canvas, originColor, newPoint(point.x, point.y + 1), matched);
            searchRecursively(canvas, originColor, newPoint(point.x, point.y - 1), matched);
            searchRecursively(canvas, originColor, newPoint(point.x + 1, point.y), matched);
            searchRecursively(canvas, originColor, newPoint(point.x - 1, point.y), matched);

        }
    }

}
