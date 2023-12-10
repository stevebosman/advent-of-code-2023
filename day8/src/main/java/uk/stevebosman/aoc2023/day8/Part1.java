package uk.stevebosman.aoc2023.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

public class Part1 {
  public static void main(final String[] args) throws IOException {
    final Instant start = Instant.now();
    try (final Stream<String> lines = Files.lines(Path.of(args[0]))) {
      final String input = Files.readString(Path.of(args[0]));
      System.out.println(new AdventMap(input).count());
    } finally {
      final Instant end = Instant.now();
      System.out.println(Duration.between(start, end));
    }
  }
}
