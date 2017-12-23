/*************************************************************************
 *  Compilation:  javac-algs4 BruteCollinearPoints.java
 *  Execution:    java-algs4  BruteCollinearPoints
 *
 *************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    /**
     * Collinear segments
     */
    private ArrayList<LineSegment> segments;

    /**
     * Finds all line segments containing 4 points
     *
     * @param points on the plain
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Invalid null points are provided");
        }

        segments = new ArrayList<>();

        if (points.length < 4) {
            return;
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Invalid null point detected");
            }

            Arrays.sort(points, point.slopeOrder());
            Point min = point;
            Point max = point;
            int collinearCount = 0;
            for (int i = 1; i < points.length; i++) {
                if (points[i] == null) {
                    throw new IllegalArgumentException("Invalid null point detected");
                }

                if (point.compareTo(points[i]) == 0) {
                    throw new IllegalArgumentException("Illegal repeated point " + point.toString() + " detected");
                }

                if (point.slopeTo(points[i]) != point.slopeTo(points[i - 1])) {

                    boolean isSegmentFull = collinearCount >= 3 && min.compareTo(point) == 0;
                    if (isSegmentFull) {
                        segments.add(new LineSegment(min, max));
                    }

                    collinearCount = 0;
                    min = point;
                    max = point;
                }

                if (min.compareTo(points[i]) > 0) {
                    min = points[i];
                }

                if (max.compareTo(points[i]) < 0) {
                    max = points[i];
                }

                collinearCount++;

                boolean isLastPointInLastSegment = i == points.length - 1 &&
                        collinearCount >= 3 &&
                        min.compareTo(point) == 0;

                if (isLastPointInLastSegment) {
                    segments.add(new LineSegment(min, max));
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
        return segments.toArray(new LineSegment[0]);
    }
}
