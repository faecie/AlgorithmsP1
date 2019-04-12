import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private static final double ZERO = 0.0;

    /**
     * X-coordinate of this point it
     */
    final int x;

    /**
     * Y-coordinate of this point
     */
    final int y;

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    double slopeTo(Point that) {
        if (that.x == this.x && that.y == this.y) {
            return Double.NEGATIVE_INFINITY;
        }

        double vertical = that.y - this.y;
        double horizon = that.x - this.x;

        return horizon == 0 ? Double.POSITIVE_INFINITY : (vertical / horizon) + ZERO;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    @Override
    public int compareTo(Point that) {
        if (that.x == this.x && that.y == this.y) {
            return 0;
        }

        if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        }

        return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    Comparator<Point> slopeOrder() {
        return new BySlope();
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    private class BySlope implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return Double.compare(Point.this.slopeTo(p1), Point.this.slopeTo(p2));
        }
    }
}
