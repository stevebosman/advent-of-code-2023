package uk.co.stevebosman.aoc2023.day19;

import java.util.regex.Pattern;

public record Part(int x, int m, int a, int s) {
  private static final Pattern PART_PATTERN = Pattern.compile("\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}");

  public static Part of(final String input) {
    final var matcher = PART_PATTERN.matcher(input);
    if (matcher.matches()) {
      return new Part(
              Integer.parseInt(matcher.group(1)),
              Integer.parseInt(matcher.group(2)),
              Integer.parseInt(matcher.group(3)),
              Integer.parseInt(matcher.group(4))
      );
    }
    throw new IllegalArgumentException("Unrecognised part: " + input);
  }

  public long totalRatings() {
    return x + m + a + s;
  }
}
