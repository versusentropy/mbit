package local.mbti.task2;


import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Merge {
    public static void main(String[] args) {

        System.out.println("Enter list of intervals to merge (e.g. [25,30] [2,19] [14, 23] [4,8])");
        System.out.println(merge(new Scanner(System.in).nextLine()));
    }

    private final static Pattern intervalPattern = Pattern.compile("(?:\\[\\s*)(\\d+)(?:\\s*,\\s*)(\\d+)(?:\\s*])");

    private static Stream<Interval> parseInput(final String input) {
        return intervalPattern.matcher(input).results()
                .map(m -> new Interval(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
    }

    /**
     * merge overlapping intervals parsed from the input String.
     *
     * @param input list of integer intervals in square-brackets.
     *              An interval consists of two integers separated by a comma.
     *              e.g. [25,30] [2,19] [14, 23] [4,8]
     *              Whitespaces are ignored
     *              Any inputs not matching the intervals' pattern are silently dropped.
     * @return Stream of merged Interval.
     * @throws NumberFormatException for numbers > Integer.MAX_VALUE
     * @throws NullPointerException  for null input
     */
    public static List<Interval> merge(final String input) {
        List<Interval> result = new ArrayList<>();
        final Interval[] last = {null};
        parseInput(input)
                .sorted(Comparator.comparingInt(o -> o.lowerBound))
                .forEach(i -> {
                    if (last[0] == null) {
                        // first iteration
                        last[0] = i;
                    } else if (last[0].upperBound >= i.lowerBound) {
                        // overlap => merge
                        last[0] = new Interval(last[0].lowerBound, Math.max(last[0].upperBound, i.upperBound));
                    } else {
                        // gap => yield last, continue with current interval
                        result.add(last[0]);
                        last[0] = i;
                    }
                });
        if (last[0] != null) {
            result.add(last[0]);
        }
        return result;
    }

    public static class Interval {
        final int lowerBound;
        final int upperBound;

        public Interval(int a, int b) {
            if (a <= b) {
                this.lowerBound = a;
                this.upperBound = b;
            } else {
                this.lowerBound = b;
                this.upperBound = a;
            }
        }

        @Override
        public String toString() {
            return "[" + lowerBound + "," + upperBound + ']';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return lowerBound == interval.lowerBound && upperBound == interval.upperBound;
        }

        @Override
        public int hashCode() {
            return Objects.hash(lowerBound, upperBound);
        }
    }
}