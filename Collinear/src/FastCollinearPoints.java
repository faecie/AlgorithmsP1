import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private static final int MIN_POINTS_IN_SEGMENT = 4;

    /**
     * Collinear lineSegments
     */
    final ArrayList<LineSegment> lineSegments;

    /**
     * Finds all line lineSegments containing 4 points
     *
     * @param points on the plain
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Invalid null points are provided");
        }

        lineSegments = new ArrayList<>();

        if (points.length < MIN_POINTS_IN_SEGMENT) {
            return;
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("Invalid null point detected");
            }

            Arrays.sort(points, point.slopeOrder());
            Point min = point;
            Point max = point;
            int collinearCount = 1;
            for (int i = 1; i < points.length; i++) {
                if (points[i] == null) {
                    throw new IllegalArgumentException("Invalid null point detected");
                }

                if (point.compareTo(points[i]) == 0) {
                    throw new IllegalArgumentException("Illegal repeated point " + point.toString() + " detected");
                }

                if (point.slopeTo(points[i]) != point.slopeTo(points[i - 1])) {

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
            }

            boolean isSegmentFull = collinearCount >= MIN_POINTS_IN_SEGMENT && min.compareTo(point) == 0;
            if (isSegmentFull) {
                lineSegments.add(new LineSegment(min, max));
            }
        }
    }

    /**
     * the number of line lineSegments
     *
     * @return the number of collinear lineSegments
     */
    public int numberOfSegments() {
        return lineSegments.size();
    }

    /**
     * The line lineSegments
     *
     * @return collinear lineSegments
     */
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }
}
