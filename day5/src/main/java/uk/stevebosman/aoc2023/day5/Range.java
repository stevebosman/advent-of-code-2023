package uk.stevebosman.aoc2023.day5;

import java.util.Arrays;
import java.util.List;

public record Range(long fromStart, long fromEnd, long toStart) {
  public static Range of(final String line) {
    final List<Long> values = Arrays.stream(line.split(" "))
                                    .map(Long::valueOf)
                                    .toList();
    return new Range(values.get(1), values.get(1) + values.get(2) - 1, values.get(0));
  }
}
