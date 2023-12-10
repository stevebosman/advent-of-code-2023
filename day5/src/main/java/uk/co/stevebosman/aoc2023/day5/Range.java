package uk.co.stevebosman.aoc2023.day5;

import java.util.Arrays;
import java.util.List;

public record Range(long fromStart, long fromEnd, long toStart, long offset) implements Comparable<Range> {
  public static Range of(final String line) {
    final List<Long> values = Arrays.stream(line.split(" "))
                                    .map(Long::valueOf)
                                    .toList();
    return new Range(values.get(1),
                     values.get(1) + values.get(2) - 1,
                     values.get(0),
                     values.get(0) - values.get(1));
  }

  @Override
  public int compareTo(final Range o) {
    return Long.compare(this.fromStart, o.fromStart);
  }

  public boolean contains(final long l) {
    return fromStart <= l && l <= fromEnd;
  }

  public long translate(final long l) {
    return l + offset;
  }
}
