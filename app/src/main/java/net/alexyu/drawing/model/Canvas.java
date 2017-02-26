package net.alexyu.drawing.model;

import net.alexyu.drawing.exception.OutOfBoundsException;

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
//            char[] boundary = new char[width + 2];
//            Arrays.fill(boundary, UL_BOUND);
//            String bString = new String(boundary);
//
//            StringBuilder sb = new StringBuilder(bString);
//            sb.append("\n");
//
//            Arrays.stream(matrix).forEach(seq -> sb.append(SIDE_BOUND).append(seq).append(SIDE_BOUND).append("\n"));
//            sb.append(bString).append("\n");
//            string = sb.toString();

            StringBuilder sb = new StringBuilder();
            Arrays.stream(matrix).forEach(array -> sb.append(array).append("\n"));
            string = sb.toString();
        }

        return string;
    }


    public static class Builder {
        public final int width;
        public final int height;

        private final char[][] matrix;

        public Builder(char[][] matrix) {
            assert (matrix == null || matrix.length == 0 || matrix[0].length == 0);
            this.matrix = matrix;
            this.height = matrix.length - 1;
            this.width = matrix[0].length - 1;
        }

        public void checkBound(int x, int y) {
            if (x <= 0 || x > width)
                throw new OutOfBoundsException();
            if (y <= 0 || y >= height)
                throw new OutOfBoundsException();
        }

        public void updateCanvas(int x, int y, char value) {
            checkBound(x, y);
            matrix[y][x] = value;
        }

        public Canvas build() {
            return new Canvas(this.matrix);
        }


        public static Builder newBuilder(int width, int height) {
            return newBuilder(width, height, SPACE);
        }

        public static Builder newBuilder(int width, int height, char value) {
            if (width <=0 || height <=0)
                throw new OutOfBoundsException();

            char[][] matrix = new char[height + 2][width + 2];

            Arrays.fill(matrix[0], UL_BOUND);
            for (int idx = 1; idx <= height; idx++) {
                Arrays.fill(matrix[idx], 1, width+1, value);
                matrix[idx][0] = '|';
                matrix[idx][width+1] = '|';
            }
            Arrays.fill(matrix[height + 1], UL_BOUND);


            return new Builder(matrix);
        }
    }

}
