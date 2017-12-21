/*************************************************************************
 *  Compilation:  javac-algs4 BruteCollinearPoints.java
 *  Execution:    java-algs4  BruteCollinearPoints
 *
 *************************************************************************/

import java.util.LinkedList;

public class BruteCollinearPoints {

    /**
     * Collinear segments
     */
    private LinkedList<LineSegment> segments;

    /**
     * Finds all line segments containing 4 points
     *
     * @param points on the plain
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Invalid null points are provided");
        }

        segments = new LinkedList<>();

        int collinearCount;
        Point minCollinearPoint;
        Point maxCollinearPoint;

        for (int currPointIx = 0; currPointIx < points.length; currPointIx++) {
            if (points[currPointIx] == null) {
                throw new IllegalArgumentException("Illegal null point provided in " + currPointIx + " place");
            }

            Point curPoint = points[currPointIx];

            minCollinearPoint = curPoint;
            maxCollinearPoint = curPoint;

            for (int slopeToPointIx = 0; slopeToPointIx < points.length; slopeToPointIx++) {
                if (points[slopeToPointIx] == null) {
                    throw new IllegalArgumentException("Illegal null point provided in " + slopeToPointIx + " place");
                }

                double slope = curPoint.slopeTo(points[slopeToPointIx]);
                if (slope == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException("Illegal repeated point " + curPoint.toString() + " detected");
                }

                collinearCount = 1;
                if (minCollinearPoint.compareTo(points[slopeToPointIx]) > 0) {
                    minCollinearPoint = points[slopeToPointIx];
                }

                if (maxCollinearPoint.compareTo(points[slopeToPointIx]) < 0) {
                    maxCollinearPoint = points[slopeToPointIx];
                }

                for (int nextPointIx = 0; nextPointIx < points.length; nextPointIx ++) {
                    if (points[nextPointIx] == null) {
                        throw new IllegalArgumentException("Illegal null point provided in " + nextPointIx + " place");
                    }

                    double nextSlope = curPoint.slopeTo(points[nextPointIx]);

                    if (nextSlope == Double.NEGATIVE_INFINITY) {
                        throw new IllegalArgumentException("Illegal repeated point " + curPoint.toString() + " detected");
                    }

                    if (nextSlope == slope) {
                        collinearCount++;

                        if (minCollinearPoint.compareTo(points[nextPointIx]) > 0) {
                            minCollinearPoint = points[nextPointIx];
                        }

                        if (maxCollinearPoint.compareTo(points[nextPointIx]) < 0) {
                            maxCollinearPoint = points[nextPointIx];
                        }

                        if (collinearCount == 3) {
                            segments.add(new LineSegment(minCollinearPoint, maxCollinearPoint));

                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * the number of line segments
     *
     * @return the number of collinear segments
     */
    public int numberOfSegments() {
        return segments.size();
    }

    /**
     * The line segments
     *
     * @return collinear segments
     */
    public LineSegment[] segments() {
        return segments.toArray( new LineSegment[0]);
    }
}
