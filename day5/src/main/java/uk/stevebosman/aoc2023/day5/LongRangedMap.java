package uk.stevebosman.aoc2023.day5;

import java.util.ArrayList;
import java.util.List;

public class LongRangedMap {
  final List<Range> ranges = new ArrayList<>();

  public Long get(final Object key) {
    Long result = null;
    if (key instanceof Long) {
      final long longKey = (Long) key;
      result = longKey;
      for (final Range range : ranges) {
        if (range.fromStart() <= longKey && longKey <= range.fromEnd()) {
          result = range.toStart() + (longKey - range.fromStart());
          break;
        }
      }
    }
    return result;
  }

  public void putRange(final Range range) {
    ranges.add(range);
  }
}
