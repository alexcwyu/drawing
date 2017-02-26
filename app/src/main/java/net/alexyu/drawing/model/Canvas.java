package net.alexyu.drawing.model;


import java.util.Arrays;

/**
 * Created by alex on 2/26/17.
 */
public class Canvas {

    public static final char UL_BOUND = '-';
    public static final char SIDE_BOUND = '|';
    public static final char SPACE = ' ';
    public static final char LINE = 'x';
    public static final char NEW_LINE = '\n';

    private final char[][] matrix;

    private String string;

    private Canvas(char[][] matrix) {
        this.matrix = matrix;
    }


    public Builder toBuilder() {
        return new Builder(this.matrix);
    }

    public String toString() {
        if (string == null) {
            StringBuilder sb = new StringBuilder();
            Arrays.stream(matrix).forEach(array -> sb.append(array).append(NEW_LINE));
            string = sb.toString();
        }

        return string;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Canvas canvas = (Canvas) o;

        return Arrays.deepEquals(matrix, canvas.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }


    public static class Builder {
        public final int width;
        public final int height;

        private final char[][] matrix;

        public Builder(char[][] input) {
            assert (input != null && input.length > 0 && input[0].length > 0);
            this.matrix = clone(input);
            this.height = input.length - 1;
            this.width = input[0].length - 1;
        }

        public static char[][] clone(char[][] input) {
            char[][] result = new char[input.length][];
            for (int i = 0; i < input.length; i++) {
                result[i] = input[i].clone();
            }
            return result;
        }

        public static Builder newBuilder(int width, int height) {
            return newBuilder(width, height, SPACE);
        }

        public static Builder newBuilder(int width, int height, char value) {
            if (width <= 0 || height <= 0)
                throw new IndexOutOfBoundsException(String.format("out of bound x=%s, y=%s", width, height));

            char[][] matrix = new char[height + 2][width + 2];

            Arrays.fill(matrix[0], UL_BOUND);
            for (int idx = 1; idx <= height; idx++) {
                Arrays.fill(matrix[idx], 1, width + 1, value);
                matrix[idx][0] = SIDE_BOUND;
                matrix[idx][width + 1] = SIDE_BOUND;
            }
            Arrays.fill(matrix[height + 1], UL_BOUND);


            return new Builder(matrix);
        }


        public boolean isInBound(Point pt) {
            return (pt.x > 0 && pt.x < width && pt.y > 0 && pt.y < height);
        }

        public void checkBound(Point pt) {
            if (!isInBound(pt))
                throw new IndexOutOfBoundsException(String.format("out of bound x=%s, y=%s", pt.x, pt.y));
        }

        public void updateCanvas(Point pt, char value) {
            checkBound(pt);
            matrix[pt.y][pt.x] = value;
        }


        public char get(Point pt) {
            checkBound(pt);
            return matrix[pt.y][pt.x];
        }

        public Canvas build() {
            return new Canvas(this.matrix);
        }
    }

}
