package uk.co.stevebosman.aoc2023.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

public class Part2 {
  public static void main(final String[] args) throws IOException {
    final var start = Instant.now();
    try {
      runIt(Path.of(args[0]));
    } finally {
      final var end = Instant.now();
      System.out.println(Duration.between(start, end));
    }
  }

  private static void runIt(final Path file) throws IOException {
    try (final Stream<String> lines = Files.lines(file).parallel()) {
      System.out.println(new FillCounter().countAll2(lines));
    }
  }
}
