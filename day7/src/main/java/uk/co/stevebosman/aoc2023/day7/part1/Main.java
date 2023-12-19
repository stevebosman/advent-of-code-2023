package uk.co.stevebosman.aoc2023.day7.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

public class Main {
  public static void main(final String[] args) throws IOException {
    final var start = Instant.now();
    try (final Stream<String> lines = Files.lines(Path.of(args[0]))) {
      System.out.println(Hands.of(lines).score());
    } finally {
      final var end = Instant.now();
      System.out.println(Duration.between(start, end));
    }
  }
}
