package uk.co.stevebosman.aoc2023.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class History {
  private final List<Integer> values;
  private final boolean constant;

  public History(final String values) {
    this(Arrays.stream(values.split(" ")).map(Integer::valueOf).toList());
  }

  public History(final List<Integer> values) {
    this.values = values;
    this.constant = new HashSet<>(values).size() == 1;
  }

  public List<Integer> getValues() {
    return values;
  }

  public boolean isConstant() {
    return constant;
  }

  public History getSubHistory() {
    final int newSize = values.size() - 1;
    final List<Integer> result = new ArrayList<>(newSize);
    if (newSize>0 && !constant) {
      for (int i = 0; i < newSize; i++) {
        result.add(values.get(i + 1) - values.get(i));
      }
    }
    return new History(result);
  }

  public int next() {
    if (constant) {
      return values.get(0);
    } else {
      return values.get(values.size() - 1) + getSubHistory().next();
    }
  }

  public int previous() {
    if (constant) {
      return values.get(0);
    } else {
      return values.get(0) - getSubHistory().previous();
    }
  }

  public static Integer sumNext(final Stream<String> lines) {
    return lines.map(s -> new History(s).next())
                .reduce(0, Integer::sum);
  }

  public static Integer sumPrevious(final Stream<String> lines) {
    return lines.map(s -> new History(s).previous())
                .reduce(0, Integer::sum);
  }
}
