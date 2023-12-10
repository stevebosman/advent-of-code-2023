package uk.stevebosman.aoc2023.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class Part2 {
  public static void main(final String[] args) throws IOException {
    final Instant start = Instant.now();
    try (final Stream<String> lines = Files.lines(Path.of(args[0]))) {
      final long result = Almanac.of(lines, true).findMinimumLocation();
      System.out.println(result);
    } finally {
      final Instant end = Instant.now();
      System.out.println(Duration.between(start, end));
    }
  }
}
