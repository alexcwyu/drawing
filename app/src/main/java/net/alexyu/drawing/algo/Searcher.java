package net.alexyu.drawing.algo;

import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.Point;

import java.util.Set;

/**
 * Created by alex on 2/26/17.
 */
public interface Searcher {

    Set<Point> searchMatchedNeighbour(Canvas.Builder canvas, Point starting);
}
