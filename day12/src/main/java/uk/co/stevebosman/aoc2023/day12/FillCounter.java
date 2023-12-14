package uk.co.stevebosman.aoc2023.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class FillCounter {
  final Map<String, Long> resultCache = new ConcurrentHashMap<>();
  final Counter counter = new Counter();

  private static boolean hasRoomForHashes(final String input, final int startingPosition, final int length) {
    final String match = "[?#]{" + length + "}[.?].*|[?#]{" + length + "}$";
    final String substring = input.substring(startingPosition);
    return substring.matches(match);
  }

  private static String buildNextActiveString(final String input, final int startingPosition, final int length) {
    final StringBuilder sb = new StringBuilder(input);
    final int endingPosition = startingPosition + length;
    sb.replace(startingPosition, endingPosition, "#".repeat(length));
    int nextQ = sb.indexOf("?");
    while (nextQ < startingPosition && nextQ > -1) {
      sb.replace(nextQ, nextQ + 1, ".");
      nextQ = sb.indexOf("?");
    }
    if (nextQ == endingPosition) {
      sb.replace(nextQ, nextQ + 1, ".");
    }
    return sb.toString();
  }

  public long count(String input, final List<Integer> counts) {
    while (input.startsWith(".")) {
      input = input.substring(1);
    }
    input = input.replaceAll("\\.+", ".");

    final Long cachedResult = resultCache.get(input + counts);

    if (cachedResult != null) {
      return cachedResult;
    }

    long count = 0;

    final int currentCount = counts.get(0);

    int nextHash = input.indexOf("#");
    if (nextHash == -1) nextHash = input.length() + 1 - currentCount;

    final int maxP = Math.min(nextHash + 1,
                       input.length() + 1 - currentCount);
    for (int startingPosition = 0; startingPosition < maxP; startingPosition++) {
      if (hasRoomForHashes(input, startingPosition, currentCount)) {
        final String nextActive = buildNextActiveString(input, startingPosition, currentCount);
          if (counts.size() > 1) {
            final long subCount = count(nextActive.substring(startingPosition + currentCount),
                                counts.subList(1, counts.size()));
            if (subCount>0) {
              count += subCount;
            }
          } else if (counts.size() == 1 && !nextActive.substring(startingPosition+currentCount).contains("#")) {
            count++;
          }
      }
    }

    resultCache.put(input + counts, count);

    return count;
  }

  public long rawCount(final String raw) {
    final String[] s = raw.split(" ");
    return count(s[0], Arrays.stream(s[1].split(","))
                             .map(Integer::valueOf)
                             .toList());
  }

  public long countAll(final Stream<String> lines) {
    return lines.map(this::rawCount)
                .reduce(Long::sum)
                .orElse(0L);
  }

  public long countAll2(final Stream<String> lines) {
    return lines.map(this::count2)
                .reduce(Long::sum)
                .orElse(0L);
  }

  public long count2(final String raw) {
    final String[] s = raw.split(" ");
    final List<Integer> counts = Arrays.stream(s[1].split(","))
                                       .map(Integer::valueOf)
                                       .toList();

    final String expandedInput = s[0] + "?" + s[0] + "?" + s[0] + "?" + s[0] + "?" + s[0];

    final List<Integer> expandedCounts = new ArrayList<>();
    expandedCounts.addAll(counts);
    expandedCounts.addAll(counts);
    expandedCounts.addAll(counts);
    expandedCounts.addAll(counts);
    expandedCounts.addAll(counts);

    final long c = count(expandedInput, expandedCounts);
    System.out.println("Finish(" + counter.increment() + "): raw = " + raw + " - " + c);
    return c;
  }

}

class Counter {
  private final Object sync = new StringBuilder();
  private int count = 0;

  public int increment() {
    synchronized (this.sync) {
      this.count++;
    }
    return this.count;
  }
}