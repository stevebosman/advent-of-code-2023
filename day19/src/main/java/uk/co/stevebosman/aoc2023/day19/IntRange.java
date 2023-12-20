package uk.co.stevebosman.aoc2023.day19;

import java.util.Optional;

public record IntRange(int min, int max) {
  public static final IntRange MAX = new IntRange(1, 4000);

  public Optional<IntRange> overlap(final IntRange other) {
    if (max < other.min || min > other.max) {
      return Optional.empty();
    }
    if (min < other.min) {
      return Optional.of(new IntRange(other.min, Math.min(this.max, other.max)));
    } else {
      return Optional.of(new IntRange(min, Math.min(this.max, other.max)));
    }
  }
}
