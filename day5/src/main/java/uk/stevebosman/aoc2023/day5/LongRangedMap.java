package uk.stevebosman.aoc2023.day5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongRangedMap {
  final List<Range> ranges = new ArrayList<>();

  public long get(final long key) {
    long result = key;
    for (final Range range : ranges) {
      if (range.contains(key)) {
        result = range.translate(key);
        break;
      }
    }
    return result;
  }

  public void sort() {
    Collections.sort(ranges);
  }

  public void putRange(final Range range) {
    ranges.add(range);
  }
}
