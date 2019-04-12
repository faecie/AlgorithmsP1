import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PointTest {

    @ParameterizedTest()
    @MethodSource("comparatorProvider")
    void testComparator(Point first, Point second, Point third, int expected) {
        int actual = first.slopeOrder().compare(second, third);

        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> comparatorProvider() {
        return Stream.of(
                Arguments.arguments(new Point(1, 2), new Point(1, 2), new Point(1, 2),  0),
                Arguments.arguments(new Point(1, 2), new Point(5, 2), new Point(1, 10),  -1),
                Arguments.arguments(new Point(1, 2), new Point(1, 2), new Point(10, 2),  -1),
                Arguments.arguments(new Point(1, 2), new Point(2, 21), new Point(2, 20),  1),
                Arguments.arguments(new Point(1, 2), new Point(2, 20), new Point(2, 20),  0)
        );
    }

    @ParameterizedTest
    @MethodSource("compareToProvider")
    void testCompareTo(Point one, Point another, int expected) {
        int actual = one.compareTo(another);

        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> compareToProvider() {
        return Stream.of(
                Arguments.arguments(new Point(1, 2), new Point(1, 2), 0),
                Arguments.arguments(new Point(10, 20), new Point(10, 24), -1),
                Arguments.arguments(new Point(10, 27), new Point(11, 24), 1)
        );
    }

    @ParameterizedTest
    @MethodSource("slopePointsProvider")
    void testSlopeMovingUp(Point one, Point other, double expected) {
        double actual = one.slopeTo(other);

        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> slopePointsProvider() {
        return Stream.of(
                Arguments.arguments(new Point(10, 27), new Point(20, 33), 0.6),
                Arguments.arguments(new Point(10, 27), new Point(20, 2), -2.5),
                Arguments.arguments(new Point(10, 27), new Point(10, 27), Double.NEGATIVE_INFINITY),
                Arguments.arguments(new Point(20, 27), new Point(10, 27), 0.0),
                Arguments.arguments(new Point(20, 27), new Point(20, 10), Double.POSITIVE_INFINITY)
        );
    }
}
