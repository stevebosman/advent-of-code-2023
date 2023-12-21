package uk.co.stevebosman.aoc2023.day21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

public class Part1 {
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
    final var input = Files.lines(file);
    System.out.println(new StepCounter(input).count(64));
  }
}
