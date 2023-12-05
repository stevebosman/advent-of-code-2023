package uk.stevebosman.aoc2023.day5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LongRangedMap implements Map<Long, Long> {
  final List<Range> ranges = new ArrayList<>();
  private long size = 0;

  @Override
  public int size() {
    return size > Long.valueOf(Integer.MAX_VALUE)
           ? Integer.MAX_VALUE
           : (int) size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0L;
  }

  @Override
  public boolean containsKey(final Object key) {
    return false;
  }

  @Override
  public boolean containsValue(final Object value) {
    return false;
  }

  @Override
  public Long get(final Object key) {
    Long result = null;
    if (key instanceof Long) {
      final long longKey = (Long) key;
      final Optional<Range> optionalRange = ranges.stream()
                                                  .filter(range -> range.fromStart() <= longKey && longKey <= range.fromEnd())
                                                  .findFirst();
      if (optionalRange.isPresent()) {
        final Range range = optionalRange.get();
        result = range.toStart() + (longKey - range.fromStart());
      } else {
        result = longKey;
      }
    }
    return result;
  }

  @Override
  public Long put(final Long key, final Long value) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public Long remove(final Object key) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void putAll(final Map<? extends Long, ? extends Long> m) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public void clear() {
    ranges.clear();
    size = 0;
  }

  @Override
  public Set<Long> keySet() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public Collection<Long> values() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public Set<Entry<Long, Long>> entrySet() {
    throw new UnsupportedOperationException("not implemented");
  }

  public void putRange(final Range range) {
    ranges.add(range);
    size += range.fromEnd() - range.fromStart() + 1;
  }
}
