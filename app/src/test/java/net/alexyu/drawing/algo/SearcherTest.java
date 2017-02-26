package net.alexyu.drawing.algo;

import com.google.common.collect.Sets;
import net.alexyu.drawing.model.Canvas;
import net.alexyu.drawing.model.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.IntStream;

import static net.alexyu.drawing.model.Point.newPoint;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alex on 2/26/17.
 */


@RunWith(Parameterized.class)
public class SearcherTest {

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {"LinearSearcher", new LinearSearcher()},
                        {"FJPSearcher", new FJPSearcher()},
                        {"RecursiveSearcher", new RecursiveSearcher()}
                }
        );
    }


    @Parameterized.Parameter
    public String name;

    @Parameterized.Parameter(1)
    public Searcher searcher;


    @Test
    public void testSearch() {

        int x = 20;
        int y = 20;

        Canvas.Builder canvas = Canvas.Builder.newBuilder(x, y);
        Set<Point> result = searcher.searchMatchedNeighbour(canvas, newPoint(1, 1));
        Set<Point> expected = Sets.newHashSet();
        IntStream.range(1, x+1)
                .forEach(i -> IntStream.range(1, y+1)
                        .forEach(j -> expected.add(newPoint(i, j))));

        assertThat(result, is(expected));

        canvas.updateCanvas(newPoint(x, y), 'Y');
        result = searcher.searchMatchedNeighbour(canvas, newPoint(1, 1));

        expected.remove(newPoint(x, y));
        assertThat(result, is(expected));


    }

}
