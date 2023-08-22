package local.mbti.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeTest {

    /**
     * this is the example from the task's description
     */
    @Test
    public void testExample() {
        assertArrayEquals(new Merge.Interval[]{new Merge.Interval(2, 23), new Merge.Interval(25, 30)}
                , Merge.merge("[25,30] [2,19] [14, 23] [4,8]").toArray());

    }

    /**
     * whitespaces before/after intervals or their separating comma are ignored
     */
    @Test
    public void testWhitespaces() {
        assertArrayEquals(new Merge.Interval[]{new Merge.Interval(2, 23), new Merge.Interval(25, 30)}
                , Merge.merge("[25 ,30]\t [2, 19][14 , 23]   [4,8 ]").toArray());

    }

    /**
     * bounds of intervals with lowerBounds > upperBound are reversed
     */
    @Test
    public void testReversedInterval() {
        assertArrayEquals(new Merge.Interval[]{new Merge.Interval(2, 23), new Merge.Interval(25, 30)}
                , Merge.merge("[30,25] [2,19] [14, 23] [4,8]").toArray());

    }

    /**
     * invalid inputs are silently dropped.
     */
    @Test
    public void testInvalidInterval() {
        assertArrayEquals(new Merge.Interval[]{new Merge.Interval(2, 23)}
                , Merge.merge("[X,25] [2,19] [14, 23] [4,8] [99, 2").toArray());

    }

    @Test
    public void testEmptyInput() {
        assertArrayEquals(new Merge.Interval[]{}
                , Merge.merge("").toArray());

    }

    /**
     * null input
     */
    @Test
    public void testNullInput() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Merge.merge(null);
        });
    }

    /**
     * Number bigger than Integer.MAX_VALUE
     */
    @Test
    public void testNumberFormatException() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Merge.merge("[1, 99999999999999999]");
        });
    }
}
