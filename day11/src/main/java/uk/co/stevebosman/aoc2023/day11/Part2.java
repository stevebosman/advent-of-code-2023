package uk.co.stevebosman.aoc2023.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

public class Part2 {
  public static void main(final String[] args) throws IOException {
    final Instant start = Instant.now();
    try {
      runIt(Path.of(args[0]));
    } finally {
      final Instant end = Instant.now();
      System.out.println(Duration.between(start, end));
    }
  }

  private static void runIt(final Path file) throws IOException {
    System.out.println(new GalaxyMap(Files.readAllLines(file), 1_000_000).sumDistances());
  }
}
